import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { WeatherService } from 'src/app/service/weather.service';
import { Today } from '../model/today';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.scss'],
})
export class WeatherComponent implements OnInit {
  weather: any;
  today: Today[] = [];

  constructor(
    private weatherService: WeatherService,
    private http: HttpClient
  ) {}
  ngOnInit(): void {}

  getcity(city: string) {
    this.weatherService.getCurrentWeather(city).subscribe(
      (data) => {
        this.weather = data;
        console.log('=======>', city);
      },
      (err) => {
        alert('wrong input');
      }
    );
  }
  getcityB(city: string) {
    this.weatherService.getCurrentWeatherBack(city).subscribe(
      (data) => {
        this.weather = data;
        console.log('=======>', city);
      },
      (err) => {
        alert('wrong input');
      }
    );
  }
}
