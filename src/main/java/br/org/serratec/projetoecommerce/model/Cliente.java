package br.org.serratec.projetoecommerce.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank(message = "Digite o e-mail")
    @Size(max = 30)
    private String email;

    @NotBlank(message = "Digite o nome de usuário. Tamanho máximo: 20 caracteres")
    @Size(max = 20)
    @Column(name = "nome_usuario")
    private String usuario;

    @NotBlank(message = "Digite seu nome completo. Tamanho máximo: 60 caracteres")
    @Size(max = 60)
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @NotBlank(message = "Digite a senha")
    private String senha;

    @NotBlank(message = "Digite o CPF sem pontos, traço e espaços")
    @Size(max = 14)
    private String cpf;

    @Size(max = 11)
    private String telefone;

    @Column(name = "data_nasc")
    private LocalDate dataNasc;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
    
    public Cliente() {
    }

    public Cliente(Long id, @NotBlank(message = "Digite o e-mail") @Size(max = 30) String email,
            @NotBlank(message = "Digite o nome de usuário. Tamanho máximo: 20 caracteres") @Size(max = 20) String usuario,
            String nomeCompleto, String senha, String cpf, String telefone, LocalDate dataNasc) {
        this.id = id;
        this.email = email;
        this.usuario = usuario;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    
   

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

    
}
