# Secure-File-Encryption-Tool
A Java-based cybersecurity project that provides secure file encryption and decryption using modern cryptographic algorithms such as AES and SHA. This project ensures confidentiality and integrity of files by allowing users to securely encrypt sensitive data and decrypt it when required.

---

#Features

- File Encryption and Decryption
- AES-based Symmetric Encryption
- SHA Hashing for Integrity Verification
- Secure Key Handling
- User-Friendly Interface / CLI
- Fast and Lightweight
- Local Secure Processing

---

# Technologies Used

- Java
- AES (Advanced Encryption Standard)
- SHA Hashing Algorithm
- Java Cryptography Architecture (JCA)
- Swing / JavaFX / CLI

---

# Project Objective

The objective of this project is to provide a secure and efficient mechanism for protecting sensitive files from unauthorized access. The tool uses cryptographic techniques to ensure data confidentiality and integrity.

---

# System Architecture


User Input  
↓  
File Selection  
↓  
AES Encryption / Decryption  
↓  
SHA Hash Verification  
↓  
Secure Output File

---

# Modules

## 1. File Handling Module

Responsible for:
- Reading input files
- Writing encrypted/decrypted files
- Managing file paths

---

## 2. Encryption Module

Uses AES algorithm to:
- Convert plaintext into ciphertext
- Secure files using secret keys

### AES (Advanced Encryption Standard)

AES is a symmetric encryption algorithm used worldwide for secure data protection. It encrypts data using the same secret key for both encryption and decryption.

---

## 3. Decryption Module

Responsible for:
- Reversing encrypted data back to original form
- Using the same secure key for decryption

---

## 4. Hashing Module

Uses SHA algorithm for:
- Data integrity verification
- Detecting file tampering

### SHA (Secure Hash Algorithm)

SHA generates a unique hash value for data. Even a small change in the file produces a completely different hash.

---

# Project Structure

```bash
Secure-File-Encryption-Tool/
│
├── Encryptor.java
├── Decryptor.java
├── KeyGeneratorUtil.java
├── FileManager.java
├── UIFrame.java
├── Main.java
```

---

# How to run the project

## Prerequisites
Java JDK 8 or above
IDE (IntelliJ IDEA / Eclipse / VS Code)

## 1. Clone the Repository
```bash
git clone https://github.com/ironhammer1122/Secure-File-Encryption-Tool.git
```
## 2. Open in IDE
Import the project into:
IntelliJ IDEA
Eclipse
VS Code
## 3. Compile and Run
Run the main Java file:
```bash
javac Main.java
java Main
```

---

# Screenshots
- Home Interface
<img width="780" height="556" alt="image" src="https://github.com/user-attachments/assets/a7e9be92-9b64-4721-ae04-945d80e508a5" />
- Selecting file for encryption
<img width="801" height="574" alt="image" src="https://github.com/user-attachments/assets/41d9c83e-e06d-4417-9570-ed0374617892" />
- Encryption
<img width="781" height="551" alt="image" src="https://github.com/user-attachments/assets/37b48537-0fb3-431e-9b19-a5d09c80269d" />
- Decryption
<img width="781" height="558" alt="image" src="https://github.com/user-attachments/assets/3c7dd5b4-b420-441c-8dc9-266317c61ef0" />

---

# Security Advantages
Prevents unauthorized file access
Ensures data confidentiality
Maintains file integrity
Uses industry-standard cryptographic algorithms

---

# Future Enhancements
Cloud Storage Integration
GUI Improvements
Multi-Factor Authentication
Drag and Drop File Upload
Database Integration
Web-Based Deployment

---

# Conclusion
The Secure File Encryption Tool demonstrates the practical implementation of cryptographic techniques using Java. By integrating AES encryption and SHA hashing, the project provides secure file protection while maintaining simplicity and efficiency.

---

# Author
IRONHAMMER
