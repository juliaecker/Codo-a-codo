package entity;

public class Autos {
    private int idAutos;
    private String marca;
    private String modelo;
    private int año;
    private double precio;
    private int stock;

    public Autos(int idAutos, String marca, String modelo, int año, double precio, int stock) {
        this.idAutos = idAutos;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
        this.stock = stock;
    }

    public Autos(String marca, String modelo, int año, double precio, int stock) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdAutos() {
        return idAutos;
    }

    public void setIdAutos(int idAutos) {
        this.idAutos = idAutos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Autos{" + "marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", precio=" + precio + ", stock=" + stock + '}';
    }
}
