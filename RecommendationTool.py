# Install autoviz using pip before running this script:
# pip install autoviz

import numpy as np
import os
import openai
from dotenv import load_dotenv
import faiss  # type: ignore
import pandas as pd

# Load Autoviz
from autoviz import AutoViz_Class
import matplotlib.pyplot as plt  # Fix for `%matplotlib inline`

AV = AutoViz_Class()

# Placeholder DataFrame for df
df = pd.DataFrame({
    'track_name': ['Song1', 'Song2'],
    'artists': ['Artist1', 'Artist2'],
    'album_name': ['Album1', 'Album2'],
    'popularity': [50, 70]
})

# Save DataFrame to a temporary CSV file for AutoViz
temp_csv_path = "temp_data.csv"
try:
    df.to_csv(temp_csv_path, index=False)

    filename = temp_csv_path  # Pass the file path to AutoViz
    target_variable = "popularity"

    dft = AV.AutoViz(
        filename,
        sep=",",
        depVar=target_variable,
        dfte=None,
        header=0,
        verbose=2,
        lowess=False,
        chart_format="svg",
        max_rows_analyzed=100,
        max_cols_analyzed=20,
        save_plot_dir=None
    )
finally:
    # Ensure temporary CSV file is deleted
    if os.path.exists(temp_csv_path):
        os.remove(temp_csv_path)

# Load environment variables
load_dotenv()  # Fixed: Removed argument to load `.env` file
openai_api_key = os.getenv("OPENAI_API_KEY")
openai.api_key = openai_api_key  # Use the loaded API key

# Function to create textual representation
def create_textual_representation(row):
    return f"""Track: {row['track_name']}, Artist: {row['artists']},
    Album: {row['album_name']}, Popularity: {row['popularity']}"""

df['textual_representation'] = df.apply(create_textual_representation, axis=1)

def get_openai_embedding(text):
    try:
        response = openai.Embedding.create(input=[text], model="text-embedding-ada-002")
        return response['data'][0]['embedding']
    except Exception as e:
        print(f"Error generating embedding: {e}")
        return np.zeros(768)  # Return a zero vector of appropriate dimension as fallback

# Generate embeddings
try:
    embeddings = np.array([get_openai_embedding(text) for text in df['textual_representation']])
except Exception as e:
    print(f"Error generating embeddings for DataFrame: {e}")
    embeddings = np.zeros((len(df), 768))  # Fallback to zero embeddings

# Create FAISS index
try:
    dimension = embeddings.shape[1]
    index = faiss.IndexFlatL2(dimension)
    index.add(embeddings)
except Exception as e:
    print(f"Error creating FAISS index: {e}")
    index = None

def recommend_similar_items(query_text, top_k=5):
    if index is None:
        print("FAISS index is not available.")
        return pd.DataFrame()  # Return an empty DataFrame as fallback

    try:
        query_embedding = np.array(get_openai_embedding(query_text)).reshape(1, -1)
        distances, indices = index.search(query_embedding, top_k)
        return df.iloc[indices[0]]
    except Exception as e:
        print(f"Error during recommendation: {e}")
        return pd.DataFrame()  # Return an empty DataFrame as fallback

query = "I like jazz"
recommendations = recommend_similar_items(query)
if not recommendations.empty:
    print(recommendations[['track_name', 'artists', 'album_name', 'popularity']])
else:
    print("No recommendations found.")
