import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { EventService, Event } from '../../services/event.service';

@Component({
  selector: 'event-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './event-list.html',
  styleUrl: './event-list.scss',
})
export class EventList implements OnInit {
  events: Event[] = [];
  loading = true;
  error: string | null = null;

  constructor(
    private eventService: EventService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.loading = true;
    this.error = null;
    this.eventService.getEvents().subscribe({
      next: (data) => {
        this.events = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load events. Please try again.';
        this.loading = false;
        console.error('Error loading events:', err);
      },
    });
  }

  viewSummary(eventId: number): void {
    this.router.navigate(['/events', eventId, 'summary']);
  }

  addFeedback(eventId: number): void {
    this.router.navigate(['/events', eventId, 'feedback']);
  }

  deleteEvent(eventId: number): void {
    if (confirm('Are you sure you want to delete this event?')) {
      this.eventService.deleteEvent(eventId).subscribe({
        next: () => {
          this.loadEvents();
        },
        error: (err) => {
          this.error = 'Failed to delete event.';
          console.error('Error deleting event:', err);
        },
      });
    }
  }
}
