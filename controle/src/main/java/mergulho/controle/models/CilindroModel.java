package mergulho.controle.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CILINDRO")
public class CilindroModel implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCilindro;
    private String dataFabricacao;
    private String serialNum;
    private String tipoCilindro;


    public UUID getIdCilindro() {
        return idCilindro;
    }

    public void setIdCilindro(UUID idCilindro) {
        this.idCilindro = idCilindro;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getTipoCilindro() {
        return tipoCilindro;
    }

    public void setTipoCilindro(String tipoCilindro) {
        this.tipoCilindro = tipoCilindro;
    }
}
