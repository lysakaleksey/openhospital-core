package org.isf.patpres.model;


import javax.persistence.*;

@Embeddable
public class Vitals {
	private float weight;
	private float height;
	private float bloodSugar;
	private float temperature;
	private String bsUnit;
	private String tempUnit;
	@Embedded
	private Bp bp;

	public Vitals() {
	}

	public Vitals(float weight, float height, float bloodSugar, float temperature, String bsUnit, String tempUnit, Bp bp) {
		this.weight = weight;
		this.height = height;
		this.bloodSugar = bloodSugar;
		this.temperature = temperature;
		this.bsUnit = bsUnit;
		this.tempUnit = tempUnit;
		this.bp = bp;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getBloodSugar() {
		return bloodSugar;
	}

	public void setBloodSugar(float bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
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

		if (Float.compare(vitals.weight, weight) != 0) return false;
		if (Float.compare(vitals.height, height) != 0) return false;
		if (Float.compare(vitals.bloodSugar, bloodSugar) != 0) return false;
		if (Float.compare(vitals.temperature, temperature) != 0) return false;
		if (bsUnit != null ? !bsUnit.equals(vitals.bsUnit) : vitals.bsUnit != null) return false;
		if (tempUnit != null ? !tempUnit.equals(vitals.tempUnit) : vitals.tempUnit != null) return false;
		return bp != null ? bp.equals(vitals.bp) : vitals.bp == null;
	}

	@Override
	public int hashCode() {
		int result = (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
		result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
		result = 31 * result + (bloodSugar != +0.0f ? Float.floatToIntBits(bloodSugar) : 0);
		result = 31 * result + (temperature != +0.0f ? Float.floatToIntBits(temperature) : 0);
		result = 31 * result + (bsUnit != null ? bsUnit.hashCode() : 0);
		result = 31 * result + (tempUnit != null ? tempUnit.hashCode() : 0);
		result = 31 * result + (bp != null ? bp.hashCode() : 0);
		return result;
	}
}
