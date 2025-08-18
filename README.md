# **Cube AI**

A **web-based drag-and-drop platform** for deep learning model training and learning.

[Project URL](https://4th-security-cube-ai-fe.vercel.app/editor)

[AI Repository](https://github.com/DguFarmSystem/4th-AI-CubeAI-AI)

[Frontend Repository](https://github.com/DguFarmSystem/4th-security-CubeAI-FE)

[Backend Repository](https://github.com/DguFarmSystem/4th-security-CubeAI-BE)

---

## **Cube AI Demo**

(Insert demo GIF or screenshots here)

Full demo video: [YouTube Link](https://youtube.com/)

---

## **Info**

**Cube AI** is a web-based AI learning platform designed to let anyone—from elementary students to non-majors—easily build and experiment with deep learning models **without programming knowledge**.

- **Block-based model design:** visually configure models with drag-and-drop
- **Real-time learning visualization:** monitor model training and inference results live
- **Built-in AI Tutor:** get personalized explanations, guidance, and troubleshooting

This enables users to intuitively understand the structure and concepts of deep learning, verify the performance of their own models, and learn by doing.

---

## **Features**

- **Drag-and-drop model builder:** Combine CNN, RNN, MLP, and more through blocks
- **Data management:** Upload local datasets or use provided samples, with preprocessing tools built-in
- **Real-time training monitor:** Track loss, accuracy, and performance graphs
- **Inference & testing:** Run predictions with trained models directly
- **AI Tutor:** Offers conceptual explanations, error handling, and step-by-step guidance
- **Curriculum integration:** Structured theory + hands-on practice learning paths

---

## **User Flow**

1. Create or load a project
2. Upload and preprocess data
3. Design the model in the **drag-and-drop editor**
4. Run training and monitor progress in real-time
5. Evaluate and test model performance
6. Consult the **AI Tutor** for guidance or troubleshooting

---

## **Build Guide**

### **Requirements**

- Python 3.9+
- Node.js 18+
- Docker (optional, for deployment)
- Git

### **Installation**

```

```

### **Run**

```

```

Then open [http://localhost:3000](http://localhost:3000/) in your browser.

---

## **Development Guide**

### **Environment**
	•	Frontend: React, TypeScript, Vite, Tailwind CSS
	•	Backend: Spring Boot (main API server), FastAPI + LangChain (AI Tutor)
	•	Database: PostgreSQL + Redis (session/cache), pgvector (vector DB for embeddings)
	•	AI: PyTorch, Flask, NumPy, Pandas, TorchVision
	•	Infra: Docker, GitHub Actions (CI/CD), Vercel (frontend deploy)
	•	Hardware: GPU with CUDA/cuDNN (for model training acceleration, optional)

### **Structure**
	•	frontend/ → React + Tailwind CSS + Zustand (UI & state management)
	•	backend/ → Spring Boot API server (auth, project management)
	•	ai/ → Flask + PyTorch modules for model training & inference
	•	tutor/ → FastAPI + LangChain + pgvector (AI tutor, RAG search)
	•	db/ → PostgreSQL (project/data store), Redis (cache/session), pgvector (vector search)
	•	devops/ → Docker & CI/CD configs (GitHub Actions, Vercel, deployment scripts)

---

## **Datasets**

- Built-in datasets: MNIST, CIFAR-10
- Custom CSV/Image uploads supported
- Automatic Train/Validation/Test split

---

## **Contribution Guide**

Cube AI is open source, and contributions are welcome:

- Bug reports
- Feature requests
- Code improvements & documentation

👉 See [CONTRIBUTING.md](https://github.com/cubeai/cubeai/blob/main/CONTRIBUTING.md)

---

## **License**

This project is licensed under the MIT License.

See [LICENSE](https://github.com/cubeai/cubeai/blob/main/LICENSE) for details.

---

## **References**

- [PyTorch](https://pytorch.org/)
- [TensorFlow](https://www.tensorflow.org/)
- [FastAPI](https://fastapi.tiangolo.com/)
- [Scikit-learn](https://scikit-learn.org/)
- [Open](https://opencv.org/)
