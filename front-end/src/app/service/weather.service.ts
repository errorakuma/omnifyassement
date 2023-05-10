import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class WeatherService {
  url = 'https://api.openweathermap.org/data/2.5/weather';
  apikey = 'b8e894e877f3ff37a958b371754f7c57';

  getCurrentWeather(city: string) {
    let params = new HttpParams()
      .set('q', city)
      .set('units', 'imperial')
      .set('appid', this.apikey);

    return this.http.get(this.url, { params });
  }
  constructor(private http: HttpClient) {}
  burl: string = 'http://localhost:8080';

  getCurrentWeatherBack(city: any): Observable<any> {
    return this.http.get(this.burl + '/api/weather/' + city);
  }
}
