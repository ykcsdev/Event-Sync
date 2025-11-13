import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
export interface Event {
  id?: number;
  title: string;
  description: string;
}

export interface Feedback {
  id?: number;
  text: string;
  sentiment?: string;
  sentiment_score?: number;
  timestamp?: string;
}
@Injectable({
  providedIn: 'root',
})
export class EventService {
  private baseUrl = environment.apiUrl.replace(/\/$/, '');
  constructor(private http: HttpClient) {}

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.baseUrl);
  }

  getEventById(eventId: number): Observable<Event> {
    return this.http.get<Event>(`${this.baseUrl}/${eventId}`);
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(this.baseUrl, event);
  }

  updateEvent(eventId: number, event: Event): Observable<Event> {
    return this.http.put<Event>(`${this.baseUrl}/${eventId}`, event);
  }

  deleteEvent(eventId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${eventId}`);
  }

  addFeedback(eventId: number, text: string): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.baseUrl}/${eventId}/feedback`, { text });
  }

  getSummary(eventId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${eventId}/summary`);
  }
}
