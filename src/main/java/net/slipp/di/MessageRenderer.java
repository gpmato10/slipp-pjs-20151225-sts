package net.slipp.di;

public class MessageRenderer {
//di를 사용하는 방법
	private MessageProvider messageProvider;
	
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
	
	public void render() {
//		di 를 적용하지 않았을때의 코드.
		MessageProvider mp = new HelloWorldMessageProvider();
		System.out.println(mp.getMessage());
		
//		hi world 버전.
//		MessageProvider mp = new HiWorldMessageProvider();
//		System.out.println(mp.getMessage());
	}
	
	public void dirender() {
		System.out.println(messageProvider.getMessage());
	}
	
	public static void main(String[] args) {
		MessageRenderer renderer = new MessageRenderer();
		renderer.render();
		
//		di를 사용한 dirender() 메소드.
		System.out.println();
		System.out.println("다음줄은 di를 사용한 dirender() 메소드.");
		
		MessageRenderer direnderer = new MessageRenderer();
		direnderer.setMessageProvider(new HiWorldMessageProvider());
		direnderer.dirender();
	}
}
