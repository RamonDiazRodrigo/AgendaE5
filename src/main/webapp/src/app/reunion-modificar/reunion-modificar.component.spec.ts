import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReunionModificarComponent } from './reunion-modificar.component';

describe('ReunionModificarComponent', () => {
  let component: ReunionModificarComponent;
  let fixture: ComponentFixture<ReunionModificarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReunionModificarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReunionModificarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
