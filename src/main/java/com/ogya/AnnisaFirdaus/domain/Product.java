package com.ogya.AnnisaFirdaus.domain;

import javax.persistence.*;

import javax.validation.constraints.NotNull;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotNull(message = "Name is mandatory")
    @Column(name="name")
    private String name;

    @NotNull(message = "Brand is mandatory")
    @Column(name="brand")
    private String brand;

    @NotNull(message = "Madein is mandatory")
    @Column(name="madein")
    private String madein;
    
    
    @NotNull(message = "Price is mandatory")
    @Column(name="price")
    private Integer price;
    
    public Product() {
    }

    public Product(@NotNull(message = "Name is mandatory") String name,
                @NotNull(message = "Brand is mandatory") String brand,
                @NotNull(message = "Madein is mandatory") String madein,
    			@NotNull(message = "Price is mandatory") Integer price){
    	this.name = name;
		this.brand = brand;
		this.madein = madein;
		this.price = price;
    }


    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMadein() {
		return madein;
	}

	public void setMadein(String madein) {
		this.madein = madein;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand
				+ ", madein=" + madein + ", price=" + price + "]";
	}
}
