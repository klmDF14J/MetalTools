package constructionCraft;

public class Module {
	
	private String module;
	
	private boolean loaded = false;
	
	public Module(String module) {
		this.module = module;
	}
	
	public String getModule() {
		return module;
	}
	
	public void load() {
		
	}
	
	public void setLoaded() {
		loaded = true;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
}
