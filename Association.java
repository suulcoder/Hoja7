/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 7*/
class Association{
	
	private String englishWord;
	private String spanishWord;

	public Association(String englishWord, String spanishWord){
		this.englishWord=englishWord;
		this.spanishWord=spanishWord;
	}

	public String getEnglish(){
		return this.englishWord;
	}

	public String getSpanish(){
		return this.spanishWord;
	}
}