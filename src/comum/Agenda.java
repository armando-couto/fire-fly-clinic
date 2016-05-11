package comum;

import java.util.Date;

public class Agenda {

    private Long id_agenda;
    private String horario;
    private Date data;
    private String descricao;

    public Agenda() {}

    public Agenda(Long id_agenda, String horario, Date data, String descricao) {
        this.id_agenda = id_agenda;
        this.horario = horario;
        this.data = data;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(Long id_agenda) {
        this.id_agenda = id_agenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}