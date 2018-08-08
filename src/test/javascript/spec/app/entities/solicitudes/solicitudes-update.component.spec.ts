/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DemoTestModule } from '../../../test.module';
import { SolicitudesUpdateComponent } from 'app/entities/solicitudes/solicitudes-update.component';
import { SolicitudesService } from 'app/entities/solicitudes/solicitudes.service';
import { Solicitudes } from 'app/shared/model/solicitudes.model';

describe('Component Tests', () => {
    describe('Solicitudes Management Update Component', () => {
        let comp: SolicitudesUpdateComponent;
        let fixture: ComponentFixture<SolicitudesUpdateComponent>;
        let service: SolicitudesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DemoTestModule],
                declarations: [SolicitudesUpdateComponent]
            })
                .overrideTemplate(SolicitudesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SolicitudesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SolicitudesService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Solicitudes(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.solicitudes = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Solicitudes();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.solicitudes = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
