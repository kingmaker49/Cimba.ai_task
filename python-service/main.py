from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import requests

app = FastAPI()

class URLRequest(BaseModel):
    url: str

@app.post("/summarize")
async def summarize_website(request: URLRequest):
    try:
        openai_api_key = "your_openai_api_key"
        response = requests.post(
            "https://api.openai.com/v1/engines/davinci-codex/completions",
            headers={
                "Authorization": f"Bearer {openai_api_key}",
                "Content-Type": "application/json"
            },
            json={
                "prompt": f"Summarize the content of the website: {request.url}",
                "max_tokens": 100
            }
        )
        summary = response.json().get("choices")[0].get("text")
        if summary:
            return {"summary": summary.strip()}
        else:
            raise HTTPException(status_code=400, detail="Failed to summarize")
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/history")
async def get_history():
    try:
        connection = psycopg2.connect(database="your_database", user="your_username", password="your_password", host="localhost", port="5432")
        cursor = connection.cursor()
        cursor.execute("SELECT url, timestamp FROM request_history ORDER BY timestamp DESC")
        records = cursor.fetchall()
        return [{"url": record[0], "timestamp": record[1]} for record in records]
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
    finally:
        cursor.close()
        connection.close()
