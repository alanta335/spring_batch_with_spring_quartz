# 📦 LocalStack S3 Commands

## 🔼 Upload a File to S3

```bash
aws --endpoint-url=http://localhost:4566 s3 cp yourfile.txt s3://mybucket/ --profile localstack
```

> Replace `yourfile.txt` with the actual path to your file.

---

## 📄 List Files in S3 Bucket

```bash
aws --endpoint-url=http://localhost:4566 s3 ls s3://mybucket/ --profile localstack
```

> This will display all files currently stored in `mybucket`.

---