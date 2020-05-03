package org.isf.patpres.model;


import javax.persistence.*;

@Embeddable
public class Vitals {
	private Float weight;
	private Float height;
	private Float bloodSugar;
	private Float temperature;
	private String bsUnit;
	private String tempUnit;
	@Embedded
	private Bp bp;

	public Vitals() {
	}

	public Vitals(Float weight, Float height, Float bloodSugar, Float temperature, String bsUnit, String tempUnit, Bp bp) {
		this.weight = weight;
		this.height = height;
		this.bloodSugar = bloodSugar;
		this.temperature = temperature;
		this.bsUnit = bsUnit;
		this.tempUnit = tempUnit;
		this.bp = bp;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getBloodSugar() {
		return bloodSugar;
	}

	public void setBloodSugar(Float bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Bp getBp() {
		return bp;
	}

	public void setBp(Bp bp) {
		this.bp = bp;
	}

	public String getBsUnit() {
		return bsUnit;
	}

	public void setBsUnit(String bsUnit) {
		this.bsUnit = bsUnit;
	}

	public String getTempUnit() {
		return tempUnit;
	}

	public void setTempUnit(String tempUnit) {
		this.tempUnit = tempUnit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vitals vitals = (Vitals) o;

		if (weight != null ? !weight.equals(vitals.weight) : vitals.weight != null) return false;
		if (height != null ? !height.equals(vitals.height) : vitals.height != null) return false;
		if (bloodSugar != null ? !bloodSugar.equals(vitals.bloodSugar) : vitals.bloodSugar != null) return false;
		if (temperature != null ? !temperature.equals(vitals.temperature) : vitals.temperature != null) return false;
		if (bsUnit != null ? !bsUnit.equals(vitals.bsUnit) : vitals.bsUnit != null) return false;
		if (tempUnit != null ? !tempUnit.equals(vitals.tempUnit) : vitals.tempUnit != null) return false;
		return bp != null ? bp.equals(vitals.bp) : vitals.bp == null;
	}

	@Override
	public int hashCode() {
		int result = weight != null ? weight.hashCode() : 0;
		result = 31 * result + (height != null ? height.hashCode() : 0);
		result = 31 * result + (bloodSugar != null ? bloodSugar.hashCode() : 0);
		result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
		result = 31 * result + (bsUnit != null ? bsUnit.hashCode() : 0);
		result = 31 * result + (tempUnit != null ? tempUnit.hashCode() : 0);
		result = 31 * result + (bp != null ? bp.hashCode() : 0);
		return result;
	}
}
