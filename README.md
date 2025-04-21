# 🌟 Kuralify – Ancient Wisdom, Instantly Delivered

**Kuralify** is a modern Android app that helps users discover meaningful **Thirukkurals** based on their emotions, thoughts, or queries. It fuses the timeless teachings of **Thiruvalluvar** with the power of **Sentence Transformers** and a sleek, modern UI to bring ancient wisdom into your daily life.

![splash](https://github.com/user-attachments/assets/d01b9419-7076-4f38-a97b-ea321df10066)

---

## 🚀 Features

- 🔍 Search Thirukkurals based on user input using **cosine similarity**
- 🧠 Hugging Face backend with precomputed **sentence embeddings**
- 💙 Modern and clean UI built with **Jetpack Compose**
- 📚 **Room Database** to cache and display Kurals offline
- ⚡ Seamless API calls using **Retrofit & Coroutines**
- 📖 Explore Kurals by **topics** and **life scenarios**

---

## 🧠 How It Works

1. The user types a query like “value of education”.
2. The app sends this to a Hugging Face API powered by **Sentence Transformers**.
3. The backend finds the **top 3 most similar Kurals** using cosine similarity.
4. The Kural IDs are matched with local data and beautifully displayed in the app.

---

## 💻 Tech Stack

| Layer         | Tools Used                              |
|---------------|------------------------------------------|
| UI            | Jetpack Compose                         |
| API Communication | Retrofit, Coroutines                |
| Local Storage | Room Database                           |
| ML Backend    | Sentence Transformers (Hugging Face)    |
| Embedding Match | Cosine Similarity (Python backend)   |

---

## 🎬 Demo Video

[![Watch the demo](https://img.youtube.com/vi/VUdyRYWG2nk/1.jpg)](https://www.youtube.com/shorts/VUdyRYWG2nk)



## 🌐 Hugging Face API

**Hugging Face Space:**  
`https://huggingface.co/spaces/pathri14/kuralify`

Returns kural_ids to fetch the full Kural text from the local JSON dataset in your app.


### ➤ Endpoint  
**POST** `/get_embedding_with_kural`

### 🔹 Sample cURL Request:

```bash
curl --location 'https://pathri14-kuralify.hf.space/get_embedding_with_kural' \
--header 'Content-Type: application/json' \
--data '{
  "text": "value of education"
}'
