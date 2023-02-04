package weather;

import java.lang.Math;
public class StevensonReading implements WeatherReading {

    public double air_temp;
    public double dew_temp;
    public double wind_speed;
    public double rain;
    double newtemp1;
    public float rel_humidity;
    public float heat_index;
    public float wind_chill;
    float c1 = -8.78469475556F;
    float c2 = 1.61139411F;
    float c3 = 2.33854883889F;
    float c4 = -0.14611605F;
    float c5 = -0.012308094F;
    float c6 = -0.0164248277778F;
    float c7 = 0.002211732F;
    float c8 = 0.00072546F;
    float c9 = -0.000003582F;

    @Override
    public int getTemperature() { return (int) this.air_temp; }

    @Override
    public int getDewPoint() {
        return (int) this.dew_temp;
    }

    @Override
    public int getWindSpeed() {
        return (int) this.wind_speed;
    }

    @Override
    public int getTotalRain() {
        return (int) this.rain;
    }

    @Override
    public int getRelativeHumidity() {
        this.rel_humidity = (int) ((6.11 * 10) * ((7.5*this.air_temp)/(237.3+this.air_temp)));
        return (int) this.rel_humidity;
    }

    @Override
    public int getHeatIndex() {
        this.heat_index = (int) (c1 + (c2*this.air_temp) + (c3*this.rain) +
                    (c4*this.air_temp*this.rain) + (c5*this.air_temp*this.air_temp)
                    + (c6*this.rain*this.rain) + (c7*(this.air_temp*this.air_temp)*this.rain)
                    + (c8*this.air_temp*this.rain*this.rain) +  (c9*this.air_temp*this.air_temp*
                    this.rain*this.rain));
        return (int) this.heat_index;
    }

    @Override
    public int getWindChill() {
        this.newtemp1 = (int) (((9.0F/5.0F)*this.air_temp) + 32.0F);
        this.wind_chill = (int) ((35.74+0.6215*newtemp1) - (35.75*Math.pow(this.wind_speed, 0.16))
            +(0.4275*this.newtemp1*Math.pow(this.wind_speed, 0.16)));
        return (int) this.wind_chill;
    }

    public StevensonReading(double air_temp, double dew_temp, double wind_speed, double rain)
        throws IllegalArgumentException {
        this.air_temp = air_temp;
        this.dew_temp = dew_temp;
        this.wind_speed = wind_speed;
        this.rain = rain;

        if (dew_temp > air_temp){
            throw new IllegalArgumentException();
        }
        if (wind_speed < 0){
            throw new IllegalArgumentException();
        }
        if (rain < 0){
            throw new IllegalArgumentException();
        }

    }

    public String toString(){
        return "Reading: "+"T = "+getTemperature()+","+" D = "+getDewPoint()+","+" v = "+getWindSpeed()+","+
            " rain = "+getTotalRain();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof StevensonReading)) {
            return false;
        }
        StevensonReading that = (StevensonReading) o;
        return this.getTemperature() == that.getTemperature()
            && this.getWindSpeed() == that.getWindSpeed()
            && this.getDewPoint() == that.getDewPoint()
            && this.getTotalRain() == that.getTotalRain();
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(this.getTemperature()) * 1000 +
            Integer.hashCode(this.getWindSpeed()) * 100 +
            Integer.hashCode(this.getDewPoint()) * 10 +
            Integer.hashCode(this.getTotalRain());

}

}
