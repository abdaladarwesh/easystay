import json
import json5
import re

with open("data.json", "r", encoding="utf-8") as f:
    content = f.read()

# remove standalone semicolons (very common JS artifact)
content = re.sub(r';\s*(?=[}\]])', '', content)   # before } or ]
content = re.sub(r';\s*\n', '\n', content)         # end of line semicolons

# remove stray semicolons between objects
content = re.sub(r'\n\s*;\s*\n', '\n', content)

try:
    data = json5.loads(content)

    with open("output.json", "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2, ensure_ascii=False)

    print("✅ Converted successfully")

except Exception as e:
    print("❌ Failed again:", e)

    # debug helper
    print("\n--- CONTEXT ---")
    pos = str(e)
    print(content[:1000])