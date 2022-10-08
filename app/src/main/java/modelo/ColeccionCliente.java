package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class ColeccionCliente {

    private HashMap<String, Cliente> mapaCliente;
    private ArrayList<Cliente> listadoClientes;

    public ColeccionCliente(){
        this.mapaCliente = new HashMap<String,Cliente>();
        this.listadoClientes = new ArrayList<>();

    }

    public void agregarCliente(Cliente cliente){
        if(!this.mapaCliente.containsKey(cliente.getId())){
            this.mapaCliente.put(cliente.getId(),cliente);
            this.listadoClientes.add(cliente);
        }
    }

    public void eliminarCliente(String idCliente){
        if(this.mapaCliente.containsKey(idCliente)){
            this.mapaCliente.remove(idCliente);
            for (int i = 0; i < this.listadoClientes.size(); i++){
                if(this.listadoClientes.get(i).getId().equals(idCliente)){
                    this.listadoClientes.remove(i);
                    break;
                }
            }
        }
    }

    public Cliente obtenerClientePorId(String idCliente){
        return this.mapaCliente.get(idCliente);
    }

    public Cliente obtenerClientePorIndice(int i){
        return this.mapaCliente.get(i);
    }

    public int obtenerDimension(){
        return this.listadoClientes.size();
    }

}
