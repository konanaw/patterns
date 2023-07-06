/*
Паттерн проектирования «Наблюдатель» (Observer) — распространенное решение
для ситуации, когда объект (называемый субъектом) должен автоматически оповещать перечень других объектов (называемых наблюдателями) при возникновении
какого-либо события (например, при изменении состояния). Обычно этот паттерн
встречается при работе с GUI-приложениями. Для компонента GUI, такого как
кнопка, регистрируется набор наблюдателей. При нажатии этой кнопки наблюдатели
оповещаются и могут выполнять определенное действие. Но сфера использования
паттерна «Наблюдатель» не ограничивается GUI. Он подойдет также в случае, когда
несколько трейдеров (наблюдателей) хотели бы реагировать на изменение цены акции (субъекта).
 */
package by.konanaw.patterns.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * В примере описывается получение данных от метеорологической станции (класс WeatherStation, рассылатель событий) и
 * использование их для вывода на экран (класс CurrentConditionsDisplay, наблюдатель событий).
 * Наблюдатель (observer) регистрируется у субъекта с помощью метода registerObserver (при этом слушатель заносится в список observers).
 * Регистрация происходит в момент создания объекта currentDisplay, т.к. метод registerObserver применяется в конструкторе.
 * При изменении погодных данных вызывается метод notifyObservers, который в свою очередь вызывает метод update
 * у всех слушателей, передавая им обновлённые данные.
 */
public class WeatherStationObserver {
    public static void main(String[] args) {
        Observer observer = new CurrentConditionsDisplay();

        WeatherStation weatherStation = new WeatherStation();
        weatherStation.registerObserver(observer);
        weatherStation.setMeasurements(29f, 65f, 745);
        weatherStation.setMeasurements(39f, 70f, 760);
        weatherStation.setMeasurements(42f, 72f, 763);
        //
        weatherStation.registerObserver((temperature, humidity, pressure) ->
                System.out.printf("2-й observer: Сейчас значения:%.1f градусов цельсия и %.1f %% влажности. Давление %d мм рт. ст.\n", temperature, humidity, pressure));
        weatherStation.setMeasurements(44f, 74f, 765);
    }
}

interface Observer {
    void update (float temperature, float humidity, int pressure);
}

interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

/**
 * WeatherStation Subject
 */
class WeatherStation implements Observable {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private int pressure;

    public WeatherStation() {
        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}

class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;
    private int pressure;

    @Override
    public void update(float temperature, float humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.printf("Сейчас значения:%.1f градусов цельсия и %.1f %% влажности. Давление %d мм рт. ст.\n", temperature, humidity, pressure);
    }
}


