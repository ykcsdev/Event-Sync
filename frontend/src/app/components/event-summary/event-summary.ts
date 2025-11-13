import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService, Event } from '../../services/event.service';

@Component({
  selector: 'app-event-summary',
  imports: [CommonModule],
  templateUrl: './event-summary.html',
  styleUrl: './event-summary.scss',
})
export class EventSummary implements OnInit {
  eventId!: number;
  event: Event | null = null;
  summary: any;
  loading = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventService: EventService
  ) {}

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.paramMap.get('eventId'));
    this.loadSummary();
    this.loadEvent();
  }

  loadEvent(): void {
    this.eventService.getEventById(this.eventId).subscribe({
      next: (data) => {
        this.event = data;
      },
      error: (err: any) => {
        console.error('Error loading event:', err);
      },
    });
  }

  loadSummary(): void {
    this.loading = true;
    this.error = null;
    this.eventService.getSummary(this.eventId).subscribe({
      next: (data) => {
        this.summary = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Failed to load summary. Please try again.';
        this.loading = false;
        console.error('Error loading summary:', err);
      },
    });
  }

  goBack(): void {
    this.router.navigate(['/events']);
  }
}
