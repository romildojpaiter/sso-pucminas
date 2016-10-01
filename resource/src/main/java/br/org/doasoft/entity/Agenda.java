package br.org.doasoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull
    private LocalDateTime date;


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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
}
