import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
@Component({
  selector: 'app-form-component',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './form-component.html',
  styleUrl: './form-component.css',

})
export class FormComponent {
  prescriptionForm = new FormGroup({
    prescriptionDate: new FormControl('', Validators.required),
    patientName: new FormControl('', Validators.required),
    patientAge: new FormControl('', [Validators.required, Validators.min(0), Validators.max(120)]),
    patientGender: new FormControl('', Validators.required),
    diagnosis: new FormControl(''),
    medicines: new FormControl(''),
    nextVisitDate: new FormControl(''),
  });

  onSubmit() {
    if (this.prescriptionForm.valid) {
      console.log('Form Submitted:', this.prescriptionForm.value);
      alert('Prescription submitted successfully!');
    } else {
      this.prescriptionForm.markAllAsTouched(); // show errors
    }
  }
}
