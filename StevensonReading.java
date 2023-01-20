package weather;

import java.lang.Math;

/**
 * StevensonReading class implements the WeatherReading interface whose the primary purpose is to
 * store a reading that was taken from a Stevenson Station.
 */
public final class StevensonReading implements WeatherReading {

  private static final float c1 = -8.78469475556F;
  private static final float c2 = 1.61139411F;
  private static final float c3 = 2.33854883889F;
  private static final float c4 = -0.14611605F;
  private static final float c5 = -0.012308094F;
  private static final float c6 = -0.0164248277778F;
  private static final float c7 = 0.002211732F;
  private static final float c8 = 0.00072546F;
  private static final float c9 = -0.000003582F;

  private final double airTemp;
  private final double dewTemp;
  private final double windSpeed;
  private final double rain;

  /**
   * This constructor is for defining the methods required in the StevensonReading and
   * throwing exceptions if any value goes beyond the said conditions.
   *
   * @param airTemp   - for calculating the air temp
   * @param dewTemp   - for calculating the dew temp
   * @param windSpeed - for calculating the wind speed
   * @param rain      - for calculating the rain
   */
  public StevensonReading(double airTemp, double dewTemp, double windSpeed, double rain)
      throws IllegalArgumentException {
    this.airTemp = airTemp;
    this.dewTemp = dewTemp;
    this.windSpeed = windSpeed;
    this.rain = rain;

    if (dewTemp > airTemp) {
      throw new IllegalArgumentException("Error: Dew Temp is greater than Air Temp!");
    }
    if (windSpeed < 0) {
      throw new IllegalArgumentException("Error: Wind Speed is a non positive number!");
    }
    if (rain < 0) {
      throw new IllegalArgumentException("Error: Rain is a non positive number!");
    }

  }

  /**
   * This method calculates the E value which we use for calculating the relative humidity.
   * @param temp - takes the temperature for calculating the E value
   * @return e - returns the e value
   */
  private double getE(final double temp) {
    return (6.11 * 10) * (7.5 * temp) / (237.3 + temp);
  }

  /**
   * This method converts the temperature from Celcius to Fahrenheit which is used in calculating
   * the wind chill.
   * @param tempCelsius - takes the temperature in Fahrenheit
   * @return temp in celcius
   */
  private double convertToFahrenheit(final double tempCelsius) {
    return 9 * tempCelsius / 5 + 32;
  }

  /**
   * This method converts the temperature from Fahrenheit to Celious which is again used in
   * calculating the wind chill.
   * @param tempFahrenheit - takes the temperature in Celcius
   * @return temp in Fahrenheit
   */
  private double convertToCelsius(final double tempFahrenheit) {
    return (tempFahrenheit - 32) * 5 / 9;
  }

  /**
   * This method calculates the relative humidity.
   * @return eDew/Air * 100
   */
  private double calculateRelativeHumidity() {
    final double eAir = getE(this.airTemp);
    final double eDew = getE(this.dewTemp);
    return eDew / eAir * 100;
  }

  /**
   * This method calculates the heat index value for the getHeatIndex() method.
   * @param temp - this paramater is the air temperature calculated before
   * @param relativeHumidity - this parameter is the relative humidity calculated before
   * @return the heat index value
   */
  private double calculateHeatIndex(double temp, double relativeHumidity) {
    return c1 + c2 * temp
        + c3 * relativeHumidity
        + c4 * temp * relativeHumidity
        + c5 * temp * temp
        + c6 * relativeHumidity * relativeHumidity
        + c7 * temp * temp * relativeHumidity
        + c8 * temp * relativeHumidity * relativeHumidity
        + c9 * temp * temp * relativeHumidity * relativeHumidity;
  }

  /**
   * This method calcualted the wind chill.
   * @param airTempFahrenheit - this parameter gives the air temp in Fahrenheit
   * @param windSpeedPowered - this parameter is a calculated field from windSpeed
   * @return wind chill value
   */
  private double calculateWindChill(double airTempFahrenheit, double windSpeedPowered) {
    double windChill = 35.74 + 0.6215 * airTempFahrenheit - 35.75 * windSpeedPowered
        + 0.4275 * airTempFahrenheit * windSpeedPowered;
    return convertToCelsius(windChill);
  }

  /**
   * This method calculates the air temperature.
   * @return airTemp value rounded value and returned in int data type
   */
  @Override public int getTemperature() {
    return (int) Math.round(this.airTemp);
  }

  /**
   * This method calculates the dew temperature.
   * @return dewTemp value rounded and returned in int data type
   */
  @Override public int getDewPoint() {
    return (int) Math.round(this.dewTemp);
  }

  /**
   * This method calculates the wind speed.
   * @return windSpeed value rounded and returned in int data type
   */
  @Override public int getWindSpeed() {
    return (int) Math.round(this.windSpeed);
  }

  /**
   * This method calculates the total rain.
   * @return rain value rounded and returned in int data type
   */
  @Override public int getTotalRain() {
    return (int) Math.round(this.rain);
  }

  /**
   *This method calculates the value from previous private method for calculating relative humidity.
   * @return relative humidity rounded and returned in int data type
   */
  @Override public int getRelativeHumidity() {
    return (int) Math.round(this.calculateRelativeHumidity());
  }

  /**
   * This method calculates the heat index using relative humidity and other constants.
   * @return heat index rounded and returned in int data type
   */
  @Override public int getHeatIndex() {
    double relativeHumidity = this.calculateRelativeHumidity();
    double heatIndex = this.calculateHeatIndex(this.airTemp, relativeHumidity);
    return (int) Math.round(heatIndex);
  }

  /**
   * This method calculates the wind chill by using the temp conversion methods.
   * @return windchill value rounded and returned in int data type
   */
  @Override public int getWindChill() {
    double airTempFahrenheit = convertToFahrenheit(this.airTemp);
    double windSpeedPowered = Math.pow(this.windSpeed, 0.16);

    double windChill = this.calculateWindChill(airTempFahrenheit, windSpeedPowered);

    return (int) Math.round(windChill);
  }

  /**
   * This method gives an output of the expected string format.
   * @return the string in the required format returning the airtemp, dewtemp, windspeed and rain
   */
  @Override public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d",
        this.getTemperature(),
        this.getDewPoint(),
        this.getWindSpeed(),
        this.getTotalRain());
  }

  /**
   * This method gives a boolean output if the four parameter values of the constructor are equal.
   * @param o - instance of StevensonReading
   * @return the boolean value if the temp, windspeed, dewtemp and rain satisfy the equals condition
   */
  @Override public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StevensonReading)) {
      return false;
    }
    final StevensonReading that = (StevensonReading) o;
    return this.getTemperature() == that.getTemperature()
        && this.getWindSpeed() == that.getWindSpeed()
        && this.getDewPoint() == that.getDewPoint()
        && this.getTotalRain() == that.getTotalRain();
  }

  /**
   * This method gives an output of an unique hashcode for every object.
   * @return a unique hashcode of the airtemp, dewtemp, windspeed and rain
   */
  @Override public int hashCode() {
    return Integer.hashCode(this.getTemperature()) * 1000 + Integer.hashCode(
        this.getWindSpeed()) * 100 + Integer.hashCode(this.getDewPoint()) * 10 + Integer.hashCode(
        this.getTotalRain());

  }

}
