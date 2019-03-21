/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 7*/
class Association{
	
	private String englishWord;
	private String spanishWord;
	public static int allAssociations;
	public int id;


	public Association(String englishWord, String spanishWord){
		this.englishWord=englishWord;
		this.spanishWord=spanishWord;
		this.allAssociations = this.allAssociations+1;
		this.id = allAssociations+1;
	}

	public String getEnglish(){
		return this.englishWord;
	}

	public String getSpanish(){
		return this.spanishWord;
	}
}