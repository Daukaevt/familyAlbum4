package com.wixsite.mupbam1.dto;

public class PhotoDto {
    private Long id;
    private String url;
    private String description;
    private int width;
    private int height;

    // Конструктор
    public PhotoDto(Long id, String url, String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }
    

    public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	// Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
    public boolean isPortrait() {
        return height > width;
    }

    public boolean isLandscape() {
        return width > height;
    }

    public boolean isSquare() {
        return width == height;
    }
}