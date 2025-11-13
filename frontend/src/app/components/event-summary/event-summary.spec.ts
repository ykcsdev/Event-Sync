import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventSummary } from './event-summary';

describe('EventSummary', () => {
  let component: EventSummary;
  let fixture: ComponentFixture<EventSummary>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventSummary]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventSummary);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
