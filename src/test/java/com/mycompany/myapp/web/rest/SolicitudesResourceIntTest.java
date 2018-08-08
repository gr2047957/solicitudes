package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.DemoApp;

import com.mycompany.myapp.domain.Solicitudes;
import com.mycompany.myapp.repository.SolicitudesRepository;
import com.mycompany.myapp.service.SolicitudesService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SolicitudesResource REST controller.
 *
 * @see SolicitudesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApp.class)
public class SolicitudesResourceIntTest {

    private static final String DEFAULT_ID_SOLICITUDES = "AAAAAAAAAA";
    private static final String UPDATED_ID_SOLICITUDES = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_CORTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_CORTO = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SolicitudesRepository solicitudesRepository;

    

    @Autowired
    private SolicitudesService solicitudesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSolicitudesMockMvc;

    private Solicitudes solicitudes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SolicitudesResource solicitudesResource = new SolicitudesResource(solicitudesService);
        this.restSolicitudesMockMvc = MockMvcBuilders.standaloneSetup(solicitudesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Solicitudes createEntity(EntityManager em) {
        Solicitudes solicitudes = new Solicitudes()
            .id_solicitudes(DEFAULT_ID_SOLICITUDES)
            .nombre_corto(DEFAULT_NOMBRE_CORTO)
            .apellido(DEFAULT_APELLIDO)
            .fecha_Registro(DEFAULT_FECHA_REGISTRO);
        return solicitudes;
    }

    @Before
    public void initTest() {
        solicitudes = createEntity(em);
    }

    @Test
    @Transactional
    public void createSolicitudes() throws Exception {
        int databaseSizeBeforeCreate = solicitudesRepository.findAll().size();

        // Create the Solicitudes
        restSolicitudesMockMvc.perform(post("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudes)))
            .andExpect(status().isCreated());

        // Validate the Solicitudes in the database
        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeCreate + 1);
        Solicitudes testSolicitudes = solicitudesList.get(solicitudesList.size() - 1);
        assertThat(testSolicitudes.getId_solicitudes()).isEqualTo(DEFAULT_ID_SOLICITUDES);
        assertThat(testSolicitudes.getNombre_corto()).isEqualTo(DEFAULT_NOMBRE_CORTO);
        assertThat(testSolicitudes.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testSolicitudes.getFecha_Registro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
    }

    @Test
    @Transactional
    public void createSolicitudesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = solicitudesRepository.findAll().size();

        // Create the Solicitudes with an existing ID
        solicitudes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSolicitudesMockMvc.perform(post("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudes)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitudes in the database
        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombre_cortoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setNombre_corto(null);

        // Create the Solicitudes, which fails.

        restSolicitudesMockMvc.perform(post("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudes)))
            .andExpect(status().isBadRequest());

        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApellidoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setApellido(null);

        // Create the Solicitudes, which fails.

        restSolicitudesMockMvc.perform(post("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudes)))
            .andExpect(status().isBadRequest());

        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFecha_RegistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setFecha_Registro(null);

        // Create the Solicitudes, which fails.

        restSolicitudesMockMvc.perform(post("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudes)))
            .andExpect(status().isBadRequest());

        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSolicitudes() throws Exception {
        // Initialize the database
        solicitudesRepository.saveAndFlush(solicitudes);

        // Get all the solicitudesList
        restSolicitudesMockMvc.perform(get("/api/solicitudes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(solicitudes.getId().intValue())))
            .andExpect(jsonPath("$.[*].id_solicitudes").value(hasItem(DEFAULT_ID_SOLICITUDES.toString())))
            .andExpect(jsonPath("$.[*].nombre_corto").value(hasItem(DEFAULT_NOMBRE_CORTO.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].fecha_Registro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())));
    }
    

    @Test
    @Transactional
    public void getSolicitudes() throws Exception {
        // Initialize the database
        solicitudesRepository.saveAndFlush(solicitudes);

        // Get the solicitudes
        restSolicitudesMockMvc.perform(get("/api/solicitudes/{id}", solicitudes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(solicitudes.getId().intValue()))
            .andExpect(jsonPath("$.id_solicitudes").value(DEFAULT_ID_SOLICITUDES.toString()))
            .andExpect(jsonPath("$.nombre_corto").value(DEFAULT_NOMBRE_CORTO.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.fecha_Registro").value(DEFAULT_FECHA_REGISTRO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSolicitudes() throws Exception {
        // Get the solicitudes
        restSolicitudesMockMvc.perform(get("/api/solicitudes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSolicitudes() throws Exception {
        // Initialize the database
        solicitudesService.save(solicitudes);

        int databaseSizeBeforeUpdate = solicitudesRepository.findAll().size();

        // Update the solicitudes
        Solicitudes updatedSolicitudes = solicitudesRepository.findById(solicitudes.getId()).get();
        // Disconnect from session so that the updates on updatedSolicitudes are not directly saved in db
        em.detach(updatedSolicitudes);
        updatedSolicitudes
            .id_solicitudes(UPDATED_ID_SOLICITUDES)
            .nombre_corto(UPDATED_NOMBRE_CORTO)
            .apellido(UPDATED_APELLIDO)
            .fecha_Registro(UPDATED_FECHA_REGISTRO);

        restSolicitudesMockMvc.perform(put("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSolicitudes)))
            .andExpect(status().isOk());

        // Validate the Solicitudes in the database
        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeUpdate);
        Solicitudes testSolicitudes = solicitudesList.get(solicitudesList.size() - 1);
        assertThat(testSolicitudes.getId_solicitudes()).isEqualTo(UPDATED_ID_SOLICITUDES);
        assertThat(testSolicitudes.getNombre_corto()).isEqualTo(UPDATED_NOMBRE_CORTO);
        assertThat(testSolicitudes.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testSolicitudes.getFecha_Registro()).isEqualTo(UPDATED_FECHA_REGISTRO);
    }

    @Test
    @Transactional
    public void updateNonExistingSolicitudes() throws Exception {
        int databaseSizeBeforeUpdate = solicitudesRepository.findAll().size();

        // Create the Solicitudes

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSolicitudesMockMvc.perform(put("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(solicitudes)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitudes in the database
        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSolicitudes() throws Exception {
        // Initialize the database
        solicitudesService.save(solicitudes);

        int databaseSizeBeforeDelete = solicitudesRepository.findAll().size();

        // Get the solicitudes
        restSolicitudesMockMvc.perform(delete("/api/solicitudes/{id}", solicitudes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Solicitudes.class);
        Solicitudes solicitudes1 = new Solicitudes();
        solicitudes1.setId(1L);
        Solicitudes solicitudes2 = new Solicitudes();
        solicitudes2.setId(solicitudes1.getId());
        assertThat(solicitudes1).isEqualTo(solicitudes2);
        solicitudes2.setId(2L);
        assertThat(solicitudes1).isNotEqualTo(solicitudes2);
        solicitudes1.setId(null);
        assertThat(solicitudes1).isNotEqualTo(solicitudes2);
    }
}
