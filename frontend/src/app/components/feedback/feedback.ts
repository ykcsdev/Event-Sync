import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService, Event } from '../../services/event.service';

@Component({
  selector: 'app-feedback',
  imports: [CommonModule, FormsModule],
  templateUrl: './feedback.html',
  styleUrl: './feedback.scss',
})
export class Feedback implements OnInit {
  eventId!: number;
  event: Event | null = null;
  feedbackText = '';
  loading = false;
  error: string | null = null;
  success = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventService: EventService
  ) {}

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.paramMap.get('eventId'));
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

  submitFeedback(): void {
    // Validation
    if (!this.feedbackText.trim()) {
      this.error = 'Feedback text is required';
      return;
    }

    this.loading = true;
    this.error = null;
    this.success = false;

    this.eventService.addFeedback(this.eventId, this.feedbackText).subscribe({
      next: () => {
        this.success = true;
        this.feedbackText = '';
        this.loading = false;
        setTimeout(() => {
          this.router.navigate(['/events']);
        }, 1500);
      },
      error: (err: any) => {
        this.error = 'Failed to submit feedback. Please try again.';
        this.loading = false;
        console.error('Error submitting feedback:', err);
      },
    });
  }

  goBack(): void {
    this.router.navigate(['/events']);
  }
}
