package Encapsular;




public class Empleados {
    private String nombre;
    private int edad;
    private String departamento;
    double salario;
    int id;

    public Empleados(String nombre, int edad, String departamento, double salario, int id) {
        this.nombre = nombre;
        this.edad = edad;
        this.departamento = departamento;
        this.salario = salario;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nombre: "+getNombre()+", Edad: "+getEdad()+", Departamento: "+getDepartamento()+
                ", Salario: "+getSalario()+", id: "+getId();
    }
}
