package com.edicsem.pe.sie.client.action;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "car")
@SessionScoped
public class Car extends BaseMantenimientoAbstractAction {

        private String model;
        private int year;
        private String manufacturer;
        private String color;
        
        public Car(String model, int year, String manufacturer, String color) {
                this.model = model;
                this.year = year;
                this.manufacturer = manufacturer;
                this.color = color;
        }

        public String getModel() {
                return model;
        }

        public void setModel(String model) {
                this.model = model;
        }

        public int getYear() {
                return year;
        }

        public void setYear(int year) {
                this.year = year;
        }
        public String getManufacturer() {
            return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
    }

    public String getColor() {
            return color;
    }

    public void setColor(String color) {
            this.color = color;
    }
}