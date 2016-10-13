package br.org.doasoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by romildopaiter on 9/28/16.
 */
@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String doadorNome;

    @NotNull
    private String laboratorioNome;
    
    @Transient
    private String dataDoacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm a")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoadorNome() {
        return doadorNome;
    }

    public void setDoadorNome(String doadorNome) {
        this.doadorNome = doadorNome;
    }

    public String getLaboratorioNome() {
        return laboratorioNome;
    }

    public void setLaboratorioNome(String laboratorioNome) {
        this.laboratorioNome = laboratorioNome;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Agenda agenda = (Agenda) o;

		if (id != null ? !id.equals(agenda.id) : agenda.id != null) return false;
		if (doadorNome != null ? !doadorNome.equals(agenda.doadorNome) : agenda.doadorNome != null) return false;
		if (laboratorioNome != null ? !laboratorioNome.equals(agenda.laboratorioNome) : agenda.laboratorioNome != null)
			return false;
		return date != null ? date.equals(agenda.date) : agenda.date == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (doadorNome != null ? doadorNome.hashCode() : 0);
		result = 31 * result + (laboratorioNome != null ? laboratorioNome.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	public String getDataDoacao() {
		return dataDoacao;
	}

	public void setDataDoacao(String dataDoacao) {
		this.dataDoacao = dataDoacao;
	}
}
