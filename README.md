# **Blockchain-Based PII Verification and Document Sharing System**

A secure and efficient system for verifying Personally Identifiable Information (PII) and sharing documents using blockchain technology. This application ensures data integrity and privacy while leveraging blockchain for tamper-proof record management.

---

## **Features**
- **PII Verification:**
  - Validates government-issued IDs (e.g., Aadhaar number) against a SQL database.
  - Ensures only authorized PII is processed.
- **Document Sharing:**
  - Upload and store documents securely on a decentralized platform using IPFS.
  - Shares validated documents via blockchain.
- **Blockchain Integration:**
  - Creates an immutable record of document transactions.
  - Ensures transparency and security in document sharing.

---

## **Technologies Used**

### **Backend**
- Spring Boot (for RESTful API development)
- SQL Database (for PII validation)
- IPFS (for decentralized document storage)
- Blockchain (e.g., Ethereum or Hyperledger, for immutable records)

### **Testing**
- Postman (for API endpoint validation)
- JUnit (for backend testing)

---

## **Installation and Setup**

### **Prerequisites**
- Java Development Kit (JDK) installed.
- SQL Database setup with required tables.
- IPFS node setup (local or cloud-based).
- Blockchain network configured (e.g., Ganache for testing).

### **Steps to Run**

#### **1. Clone the Repository**
```bash
git clone https://github.com/your-username/blockchain-pii-verification.git
cd blockchain-pii-verification
```

#### **2. Configure Backend**
- Update `application.properties` in the backend folder with:
  - SQL Database credentials.
  - IPFS configuration.
  - Blockchain node details.

#### **3. Run Backend**
- Navigate to the backend directory and build the project:
  ```bash
  mvn clean install
  ```
- Start the Spring Boot application:
  ```bash
  mvn spring-boot:run
  ```

---

## **API Endpoints**

| Method | Endpoint                  | Description                      |
|--------|---------------------------|----------------------------------|
| POST   | `/api/pii/verify`         | Verifies PII against the database |
| POST   | `/api/documents/upload`  | Uploads document to IPFS         |
| GET    | `/api/documents/{id}`     | Retrieves document details       |
| POST   | `/api/blockchain/record` | Records transaction on blockchain |

---

## **Testing**
1. Use **Postman** for API validation:
   - **PII Verification:**
     ```json
     {
       "aadhaar": "1234-5678-9101",
       "name": "John Doe",
       "dob": "1990-01-01"
     }
     ```
   - **Document Upload:** Upload a file in `multipart/form-data` format.
2. Run JUnit tests for backend logic.

---

## **Future Enhancements**
- Integrate user authentication for secure access.
- Expand support for additional government IDs.
- Build a mobile application for enhanced accessibility.

---
