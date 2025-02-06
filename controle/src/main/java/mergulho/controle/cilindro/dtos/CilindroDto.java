package mergulho.controle.cilindro.dtos;

import jakarta.validation.constraints.NotBlank;

public class CilindroDto {

        public CilindroDto(String dataFabricacao, String serialNum, String tipoCilindro) {
        }

        @NotBlank(message = "Data de fabricação é obrigatória")
        private String dataFabricacao;

        @NotBlank(message = "Serial é obrigatório")
        private String serialNum;

        @NotBlank(message = "Tipo de cilindro é obrigatório")
        private String tipoCilindro;

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


