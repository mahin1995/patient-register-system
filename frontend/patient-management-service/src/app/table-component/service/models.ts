export interface PaginationRequest {
  page: number;
  size: number;
}

export interface PaginationResponse<T> {
  content: T[];
  pageNumber: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}
export interface Patient {
  id: number;
  prescriptionDate?: string;
  patientName?: string;
  patientAge?: number;
  patientGender?: string;
  diagnosis?: string;
  medicines?: string;
  nextVisitDate?: string;
  phoneNumber?: string | null;
  doctorId?: string | null;
}
