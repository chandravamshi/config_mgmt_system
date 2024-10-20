import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Config } from './interfaces/config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  
  private apiUrl = 'http://localhost:8082/api/configs'; //  backend API URL

  constructor(private http: HttpClient) {}

  getConfigs(): Observable<Config[]> {
    return this.http.get<Config[]>(this.apiUrl);
  }

  createConfig(config: Config): Observable<Config> {
    return this.http.post<Config>(this.apiUrl, config);
  }
  

  updateConfig(id: number, config: Config): Observable<Config> {
    return this.http.put<Config>(`${this.apiUrl}/${id}`, config);
  }

  deleteConfig(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
