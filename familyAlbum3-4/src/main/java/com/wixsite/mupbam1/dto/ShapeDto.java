package com.wixsite.mupbam1.dto;

public class ShapeDto {
    private String type;  // Тип фигуры (circle, square, triangle)
    private int size;     // Размер фигуры
    private String color; // Цвет фигуры в формате HEX (#RRGGBB)

    // Конструктор
    public ShapeDto(String type, int size, String color) {
        this.type = type;
        this.size = size;
        this.color = color;
    }

    // Геттеры и сеттеры
    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }
}