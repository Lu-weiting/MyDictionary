# MyDictionary (我的日英中線上翻譯字典）
> Main Function （主要有哪些功能）
>> * 輸入英文翻譯成中文之外提供5個由chatGPT提供的托福考試容易出現的語句，以及同義相近詞的造句（都具有對應的語音能播放）
>> 相反輸入中文也是
>> * 輸入中或英文能夠翻譯成對應日文，以及提供5個單字例句，且都具有自然語音能播放

> What I use (包含了哪些技術）
>> * 由spring boot框架、Thymeleaf前端模板、RESTful等等概念開發的簡單Web
>> * 利用OpenAI ChatGPT3.5 api其中的 text-davinci-003 模型來得到翻譯內容
>> * 利用Google Text-To-Speech api 以及 抓取yahoo字典翻譯語音來得到翻譯內容(中日英)的語音包<br>
>> 以一個句子來說TTF提供的語音包有比較穩定、自然的效果，單字的語音則透過抓取yahoo字典語音包下載（比對api發送請求快些）
>> *利用javascript來播放所屬音檔



