package com.xiaoqingchun.model;

public class Content {
    private Integer id;

    private Integer price;

    private String title;

    private byte[] icon;

    private String abstract_;

    private byte[] text;
    
    private boolean isbuy;

    public Content(){}

	public Content(Integer price, String title, byte[] icon, String abstract_, byte[] text) {
		super();
		this.price = price;
		this.title = title;
		this.icon = icon;
		this.abstract_ = abstract_;
		this.text = text;
	}

	public boolean isIsbuy() {
		return isbuy;
	}

	public void setIsbuy(boolean isbuy) {
		this.isbuy = isbuy;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getAbstract_() {
        return abstract_;
    }

    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }
}
