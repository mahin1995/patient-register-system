import { Component, signal, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PatientService } from './service/patient-service';
import { Patient, PaginationRequest } from './service/models';

@Component({
  selector: 'app-table-component',
  templateUrl: './table-component.html',
  styleUrls: ['./table-component.css'],
})
export class TableComponent implements OnInit {
  patients = signal<Patient[]>([]);
  pageNumber = 1;
  pageSize = 10;
  totalPages = 0;

  constructor(private router: Router, private patientService: PatientService) {}

  ngOnInit() {
    this.loadPatients({ page: 1, size: this.pageSize });
  }

  loadPatients(pagination: PaginationRequest) {
    this.patientService.getPatients(pagination).subscribe({
      next: (res:any) => {
        this.patients.set(res.content);
        this.pageNumber = res.pageNumber;
        this.pageSize = res.pageSize;
        this.totalPages = res.totalPages;
      },
      error: (err:any) => console.error('Error loading patients', err),
    });
  }

  goToForm() {
    this.router.navigate(['/patient-entry-form']);
  }

  edit(patient: Patient) {
    console.log('Edit', patient);
  }

  delete(patient: Patient) {
    console.log('Delete', patient);
  }

  nextPage() {
    if (this.pageNumber + 1 < this.totalPages) {
      this.loadPatients({ page: this.pageNumber + 1, size: this.pageSize });
    }
  }

  prevPage() {
    if (this.pageNumber > 0) {
      this.loadPatients({ page: this.pageNumber - 1, size: this.pageSize });
    }
  }
}
