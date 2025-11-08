import { Routes } from '@angular/router';
import { HomeComponent } from './home-component/home-component';
import { FormComponent } from './form-component/form-component';
import { bootstrapApplication } from '@angular/platform-browser';
import { TableComponent } from './table-component/table-component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'patient-entry-form', component: FormComponent },
  { path: 'patient-list', component: TableComponent },
];

