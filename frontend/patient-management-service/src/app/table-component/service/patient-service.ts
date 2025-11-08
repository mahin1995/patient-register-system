import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient, PaginationRequest, PaginationResponse } from './models';
@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private apiUrl = 'http://localhost:8088/api/v1/prescriptions/list';

  constructor(private http: HttpClient) {}

  getPatients(pagination: PaginationRequest): Observable<PaginationResponse<Patient>> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization:
        'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0FETUlOIl0sInN1YiI6Im1haGluIiwiaWF0IjoxNzYyNjI2NDE5LCJleHAiOjE3NjI2NjI0MTl9.OUjnxqXHuOVbpV33SktK5ILLAaFg5uIXsJKJIAKADY4', // replace with actual token
    });

    let result = this.http.post<PaginationResponse<Patient>>(
      this.apiUrl,
      { paginationRequest: pagination },
      { headers }
    );
    console.log(result);
    return result;
  }
}


 // your models


