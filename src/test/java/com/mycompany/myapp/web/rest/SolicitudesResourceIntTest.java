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

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_SOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_SOLICITUD = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_SOLICITUD = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONA_A_SUSTITUIR = "AAAAAAAAAA";
    private static final String UPDATED_PERSONA_A_SUSTITUIR = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_UNIDAD_NEGOCIO = "AAAAAAAAAA";
    private static final String UPDATED_UNIDAD_NEGOCIO = "BBBBBBBBBB";

    private static final String DEFAULT_CECO = "AAAAAAAAAA";
    private static final String UPDATED_CECO = "BBBBBBBBBB";

    private static final String DEFAULT_REPORTA_A = "AAAAAAAAAA";
    private static final String UPDATED_REPORTA_A = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICIO = "AAAAAAAAAA";
    private static final String UPDATED_SERVICIO = "BBBBBBBBBB";

    private static final String DEFAULT_PERIODOS_ASIGNACION = "AAAAAAAAAA";
    private static final String UPDATED_PERIODOS_ASIGNACION = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_CANDIDATO_PROPUESTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_CANDIDATO_PROPUESTO = "BBBBBBBBBB";

    private static final String DEFAULT_PROVEEDOR_CANDIDATO_PROPUESTO = "AAAAAAAAAA";
    private static final String UPDATED_PROVEEDOR_CANDIDATO_PROPUESTO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISPONIBILIDAD_VIAJAR = false;
    private static final Boolean UPDATED_DISPONIBILIDAD_VIAJAR = true;

    private static final Boolean DEFAULT_EXPERIENCIA_SECTOR_BANCARIO = false;
    private static final Boolean UPDATED_EXPERIENCIA_SECTOR_BANCARIO = true;

    private static final String DEFAULT_NIVEL_BANCARIO = "AAAAAAAAAA";
    private static final String UPDATED_NIVEL_BANCARIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIORIDAD = 1;
    private static final Integer UPDATED_PRIORIDAD = 2;

    private static final String DEFAULT_TIPO_RECURSO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_RECURSO = "BBBBBBBBBB";

    private static final String DEFAULT_EXPERIENCIA_BANCARIA = "AAAAAAAAAA";
    private static final String UPDATED_EXPERIENCIA_BANCARIA = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_TECNICO = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_TECNICO = "BBBBBBBBBB";

    private static final String DEFAULT_OBLIGATORIO = "AAAAAAAAAA";
    private static final String UPDATED_OBLIGATORIO = "BBBBBBBBBB";

    private static final String DEFAULT_EXPERIENCIA = "AAAAAAAAAA";
    private static final String UPDATED_EXPERIENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_SOFT_SKILL = "AAAAAAAAAA";
    private static final String UPDATED_SOFT_SKILL = "BBBBBBBBBB";

    private static final String DEFAULT_NIVEL_SOFT_SKILL = "AAAAAAAAAA";
    private static final String UPDATED_NIVEL_SOFT_SKILL = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_CANDIDATO_PROPUESTO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_CANDIDATO_PROPUESTO = "BBBBBBBBBB";

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
            .fecha_Registro(DEFAULT_FECHA_REGISTRO)
            .solicitante(DEFAULT_SOLICITANTE)
            .tipoSolicitud(DEFAULT_TIPO_SOLICITUD)
            .personaASustituir(DEFAULT_PERSONA_A_SUSTITUIR)
            .direccion(DEFAULT_DIRECCION)
            .unidadNegocio(DEFAULT_UNIDAD_NEGOCIO)
            .ceco(DEFAULT_CECO)
            .reportaA(DEFAULT_REPORTA_A)
            .servicio(DEFAULT_SERVICIO)
            .periodosAsignacion(DEFAULT_PERIODOS_ASIGNACION)
            .nombreCandidatoPropuesto(DEFAULT_NOMBRE_CANDIDATO_PROPUESTO)
            .proveedorCandidatoPropuesto(DEFAULT_PROVEEDOR_CANDIDATO_PROPUESTO)
            .disponibilidadViajar(DEFAULT_DISPONIBILIDAD_VIAJAR)
            .experienciaSectorBancario(DEFAULT_EXPERIENCIA_SECTOR_BANCARIO)
            .nivelBancario(DEFAULT_NIVEL_BANCARIO)
            .prioridad(DEFAULT_PRIORIDAD)
            .tipoRecurso(DEFAULT_TIPO_RECURSO)
            .experienciaBancaria(DEFAULT_EXPERIENCIA_BANCARIA)
            .skillTecnico(DEFAULT_SKILL_TECNICO)
            .obligatorio(DEFAULT_OBLIGATORIO)
            .experiencia(DEFAULT_EXPERIENCIA)
            .softSkill(DEFAULT_SOFT_SKILL)
            .nivelSoftSkill(DEFAULT_NIVEL_SOFT_SKILL)
            .apellidoCandidatoPropuesto(DEFAULT_APELLIDO_CANDIDATO_PROPUESTO);
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
        assertThat(testSolicitudes.getFecha_Registro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
        assertThat(testSolicitudes.getSolicitante()).isEqualTo(DEFAULT_SOLICITANTE);
        assertThat(testSolicitudes.getTipoSolicitud()).isEqualTo(DEFAULT_TIPO_SOLICITUD);
        assertThat(testSolicitudes.getPersonaASustituir()).isEqualTo(DEFAULT_PERSONA_A_SUSTITUIR);
        assertThat(testSolicitudes.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testSolicitudes.getUnidadNegocio()).isEqualTo(DEFAULT_UNIDAD_NEGOCIO);
        assertThat(testSolicitudes.getCeco()).isEqualTo(DEFAULT_CECO);
        assertThat(testSolicitudes.getReportaA()).isEqualTo(DEFAULT_REPORTA_A);
        assertThat(testSolicitudes.getServicio()).isEqualTo(DEFAULT_SERVICIO);
        assertThat(testSolicitudes.getPeriodosAsignacion()).isEqualTo(DEFAULT_PERIODOS_ASIGNACION);
        assertThat(testSolicitudes.getNombreCandidatoPropuesto()).isEqualTo(DEFAULT_NOMBRE_CANDIDATO_PROPUESTO);
        assertThat(testSolicitudes.getProveedorCandidatoPropuesto()).isEqualTo(DEFAULT_PROVEEDOR_CANDIDATO_PROPUESTO);
        assertThat(testSolicitudes.isDisponibilidadViajar()).isEqualTo(DEFAULT_DISPONIBILIDAD_VIAJAR);
        assertThat(testSolicitudes.isExperienciaSectorBancario()).isEqualTo(DEFAULT_EXPERIENCIA_SECTOR_BANCARIO);
        assertThat(testSolicitudes.getNivelBancario()).isEqualTo(DEFAULT_NIVEL_BANCARIO);
        assertThat(testSolicitudes.getPrioridad()).isEqualTo(DEFAULT_PRIORIDAD);
        assertThat(testSolicitudes.getTipoRecurso()).isEqualTo(DEFAULT_TIPO_RECURSO);
        assertThat(testSolicitudes.getExperienciaBancaria()).isEqualTo(DEFAULT_EXPERIENCIA_BANCARIA);
        assertThat(testSolicitudes.getSkillTecnico()).isEqualTo(DEFAULT_SKILL_TECNICO);
        assertThat(testSolicitudes.getObligatorio()).isEqualTo(DEFAULT_OBLIGATORIO);
        assertThat(testSolicitudes.getExperiencia()).isEqualTo(DEFAULT_EXPERIENCIA);
        assertThat(testSolicitudes.getSoftSkill()).isEqualTo(DEFAULT_SOFT_SKILL);
        assertThat(testSolicitudes.getNivelSoftSkill()).isEqualTo(DEFAULT_NIVEL_SOFT_SKILL);
        assertThat(testSolicitudes.getApellidoCandidatoPropuesto()).isEqualTo(DEFAULT_APELLIDO_CANDIDATO_PROPUESTO);
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
    public void checkSolicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setSolicitante(null);

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
    public void checkTipoSolicitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setTipoSolicitud(null);

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
    public void checkPersonaASustituirIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setPersonaASustituir(null);

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
    public void checkDireccionIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setDireccion(null);

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
    public void checkUnidadNegocioIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setUnidadNegocio(null);

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
    public void checkCecoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setCeco(null);

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
    public void checkReportaAIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setReportaA(null);

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
    public void checkServicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setServicio(null);

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
    public void checkPeriodosAsignacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setPeriodosAsignacion(null);

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
    public void checkNombreCandidatoPropuestoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setNombreCandidatoPropuesto(null);

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
    public void checkProveedorCandidatoPropuestoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setProveedorCandidatoPropuesto(null);

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
    public void checkDisponibilidadViajarIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setDisponibilidadViajar(null);

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
    public void checkExperienciaSectorBancarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setExperienciaSectorBancario(null);

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
    public void checkNivelBancarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setNivelBancario(null);

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
    public void checkPrioridadIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setPrioridad(null);

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
    public void checkTipoRecursoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setTipoRecurso(null);

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
    public void checkExperienciaBancariaIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setExperienciaBancaria(null);

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
    public void checkSkillTecnicoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setSkillTecnico(null);

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
    public void checkObligatorioIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setObligatorio(null);

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
    public void checkExperienciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setExperiencia(null);

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
    public void checkSoftSkillIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setSoftSkill(null);

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
    public void checkNivelSoftSkillIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setNivelSoftSkill(null);

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
    public void checkApellidoCandidatoPropuestoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitudesRepository.findAll().size();
        // set the field null
        solicitudes.setApellidoCandidatoPropuesto(null);

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
            .andExpect(jsonPath("$.[*].fecha_Registro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].solicitante").value(hasItem(DEFAULT_SOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].tipoSolicitud").value(hasItem(DEFAULT_TIPO_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].personaASustituir").value(hasItem(DEFAULT_PERSONA_A_SUSTITUIR.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].unidadNegocio").value(hasItem(DEFAULT_UNIDAD_NEGOCIO.toString())))
            .andExpect(jsonPath("$.[*].ceco").value(hasItem(DEFAULT_CECO.toString())))
            .andExpect(jsonPath("$.[*].reportaA").value(hasItem(DEFAULT_REPORTA_A.toString())))
            .andExpect(jsonPath("$.[*].servicio").value(hasItem(DEFAULT_SERVICIO.toString())))
            .andExpect(jsonPath("$.[*].periodosAsignacion").value(hasItem(DEFAULT_PERIODOS_ASIGNACION.toString())))
            .andExpect(jsonPath("$.[*].nombreCandidatoPropuesto").value(hasItem(DEFAULT_NOMBRE_CANDIDATO_PROPUESTO.toString())))
            .andExpect(jsonPath("$.[*].proveedorCandidatoPropuesto").value(hasItem(DEFAULT_PROVEEDOR_CANDIDATO_PROPUESTO.toString())))
            .andExpect(jsonPath("$.[*].disponibilidadViajar").value(hasItem(DEFAULT_DISPONIBILIDAD_VIAJAR.booleanValue())))
            .andExpect(jsonPath("$.[*].experienciaSectorBancario").value(hasItem(DEFAULT_EXPERIENCIA_SECTOR_BANCARIO.booleanValue())))
            .andExpect(jsonPath("$.[*].nivelBancario").value(hasItem(DEFAULT_NIVEL_BANCARIO.toString())))
            .andExpect(jsonPath("$.[*].prioridad").value(hasItem(DEFAULT_PRIORIDAD)))
            .andExpect(jsonPath("$.[*].tipoRecurso").value(hasItem(DEFAULT_TIPO_RECURSO.toString())))
            .andExpect(jsonPath("$.[*].experienciaBancaria").value(hasItem(DEFAULT_EXPERIENCIA_BANCARIA.toString())))
            .andExpect(jsonPath("$.[*].skillTecnico").value(hasItem(DEFAULT_SKILL_TECNICO.toString())))
            .andExpect(jsonPath("$.[*].obligatorio").value(hasItem(DEFAULT_OBLIGATORIO.toString())))
            .andExpect(jsonPath("$.[*].experiencia").value(hasItem(DEFAULT_EXPERIENCIA.toString())))
            .andExpect(jsonPath("$.[*].softSkill").value(hasItem(DEFAULT_SOFT_SKILL.toString())))
            .andExpect(jsonPath("$.[*].nivelSoftSkill").value(hasItem(DEFAULT_NIVEL_SOFT_SKILL.toString())))
            .andExpect(jsonPath("$.[*].apellidoCandidatoPropuesto").value(hasItem(DEFAULT_APELLIDO_CANDIDATO_PROPUESTO.toString())));
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
            .andExpect(jsonPath("$.fecha_Registro").value(DEFAULT_FECHA_REGISTRO.toString()))
            .andExpect(jsonPath("$.solicitante").value(DEFAULT_SOLICITANTE.toString()))
            .andExpect(jsonPath("$.tipoSolicitud").value(DEFAULT_TIPO_SOLICITUD.toString()))
            .andExpect(jsonPath("$.personaASustituir").value(DEFAULT_PERSONA_A_SUSTITUIR.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.unidadNegocio").value(DEFAULT_UNIDAD_NEGOCIO.toString()))
            .andExpect(jsonPath("$.ceco").value(DEFAULT_CECO.toString()))
            .andExpect(jsonPath("$.reportaA").value(DEFAULT_REPORTA_A.toString()))
            .andExpect(jsonPath("$.servicio").value(DEFAULT_SERVICIO.toString()))
            .andExpect(jsonPath("$.periodosAsignacion").value(DEFAULT_PERIODOS_ASIGNACION.toString()))
            .andExpect(jsonPath("$.nombreCandidatoPropuesto").value(DEFAULT_NOMBRE_CANDIDATO_PROPUESTO.toString()))
            .andExpect(jsonPath("$.proveedorCandidatoPropuesto").value(DEFAULT_PROVEEDOR_CANDIDATO_PROPUESTO.toString()))
            .andExpect(jsonPath("$.disponibilidadViajar").value(DEFAULT_DISPONIBILIDAD_VIAJAR.booleanValue()))
            .andExpect(jsonPath("$.experienciaSectorBancario").value(DEFAULT_EXPERIENCIA_SECTOR_BANCARIO.booleanValue()))
            .andExpect(jsonPath("$.nivelBancario").value(DEFAULT_NIVEL_BANCARIO.toString()))
            .andExpect(jsonPath("$.prioridad").value(DEFAULT_PRIORIDAD))
            .andExpect(jsonPath("$.tipoRecurso").value(DEFAULT_TIPO_RECURSO.toString()))
            .andExpect(jsonPath("$.experienciaBancaria").value(DEFAULT_EXPERIENCIA_BANCARIA.toString()))
            .andExpect(jsonPath("$.skillTecnico").value(DEFAULT_SKILL_TECNICO.toString()))
            .andExpect(jsonPath("$.obligatorio").value(DEFAULT_OBLIGATORIO.toString()))
            .andExpect(jsonPath("$.experiencia").value(DEFAULT_EXPERIENCIA.toString()))
            .andExpect(jsonPath("$.softSkill").value(DEFAULT_SOFT_SKILL.toString()))
            .andExpect(jsonPath("$.nivelSoftSkill").value(DEFAULT_NIVEL_SOFT_SKILL.toString()))
            .andExpect(jsonPath("$.apellidoCandidatoPropuesto").value(DEFAULT_APELLIDO_CANDIDATO_PROPUESTO.toString()));
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
            .fecha_Registro(UPDATED_FECHA_REGISTRO)
            .solicitante(UPDATED_SOLICITANTE)
            .tipoSolicitud(UPDATED_TIPO_SOLICITUD)
            .personaASustituir(UPDATED_PERSONA_A_SUSTITUIR)
            .direccion(UPDATED_DIRECCION)
            .unidadNegocio(UPDATED_UNIDAD_NEGOCIO)
            .ceco(UPDATED_CECO)
            .reportaA(UPDATED_REPORTA_A)
            .servicio(UPDATED_SERVICIO)
            .periodosAsignacion(UPDATED_PERIODOS_ASIGNACION)
            .nombreCandidatoPropuesto(UPDATED_NOMBRE_CANDIDATO_PROPUESTO)
            .proveedorCandidatoPropuesto(UPDATED_PROVEEDOR_CANDIDATO_PROPUESTO)
            .disponibilidadViajar(UPDATED_DISPONIBILIDAD_VIAJAR)
            .experienciaSectorBancario(UPDATED_EXPERIENCIA_SECTOR_BANCARIO)
            .nivelBancario(UPDATED_NIVEL_BANCARIO)
            .prioridad(UPDATED_PRIORIDAD)
            .tipoRecurso(UPDATED_TIPO_RECURSO)
            .experienciaBancaria(UPDATED_EXPERIENCIA_BANCARIA)
            .skillTecnico(UPDATED_SKILL_TECNICO)
            .obligatorio(UPDATED_OBLIGATORIO)
            .experiencia(UPDATED_EXPERIENCIA)
            .softSkill(UPDATED_SOFT_SKILL)
            .nivelSoftSkill(UPDATED_NIVEL_SOFT_SKILL)
            .apellidoCandidatoPropuesto(UPDATED_APELLIDO_CANDIDATO_PROPUESTO);

        restSolicitudesMockMvc.perform(put("/api/solicitudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSolicitudes)))
            .andExpect(status().isOk());

        // Validate the Solicitudes in the database
        List<Solicitudes> solicitudesList = solicitudesRepository.findAll();
        assertThat(solicitudesList).hasSize(databaseSizeBeforeUpdate);
        Solicitudes testSolicitudes = solicitudesList.get(solicitudesList.size() - 1);
        assertThat(testSolicitudes.getId_solicitudes()).isEqualTo(UPDATED_ID_SOLICITUDES);
        assertThat(testSolicitudes.getFecha_Registro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testSolicitudes.getSolicitante()).isEqualTo(UPDATED_SOLICITANTE);
        assertThat(testSolicitudes.getTipoSolicitud()).isEqualTo(UPDATED_TIPO_SOLICITUD);
        assertThat(testSolicitudes.getPersonaASustituir()).isEqualTo(UPDATED_PERSONA_A_SUSTITUIR);
        assertThat(testSolicitudes.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testSolicitudes.getUnidadNegocio()).isEqualTo(UPDATED_UNIDAD_NEGOCIO);
        assertThat(testSolicitudes.getCeco()).isEqualTo(UPDATED_CECO);
        assertThat(testSolicitudes.getReportaA()).isEqualTo(UPDATED_REPORTA_A);
        assertThat(testSolicitudes.getServicio()).isEqualTo(UPDATED_SERVICIO);
        assertThat(testSolicitudes.getPeriodosAsignacion()).isEqualTo(UPDATED_PERIODOS_ASIGNACION);
        assertThat(testSolicitudes.getNombreCandidatoPropuesto()).isEqualTo(UPDATED_NOMBRE_CANDIDATO_PROPUESTO);
        assertThat(testSolicitudes.getProveedorCandidatoPropuesto()).isEqualTo(UPDATED_PROVEEDOR_CANDIDATO_PROPUESTO);
        assertThat(testSolicitudes.isDisponibilidadViajar()).isEqualTo(UPDATED_DISPONIBILIDAD_VIAJAR);
        assertThat(testSolicitudes.isExperienciaSectorBancario()).isEqualTo(UPDATED_EXPERIENCIA_SECTOR_BANCARIO);
        assertThat(testSolicitudes.getNivelBancario()).isEqualTo(UPDATED_NIVEL_BANCARIO);
        assertThat(testSolicitudes.getPrioridad()).isEqualTo(UPDATED_PRIORIDAD);
        assertThat(testSolicitudes.getTipoRecurso()).isEqualTo(UPDATED_TIPO_RECURSO);
        assertThat(testSolicitudes.getExperienciaBancaria()).isEqualTo(UPDATED_EXPERIENCIA_BANCARIA);
        assertThat(testSolicitudes.getSkillTecnico()).isEqualTo(UPDATED_SKILL_TECNICO);
        assertThat(testSolicitudes.getObligatorio()).isEqualTo(UPDATED_OBLIGATORIO);
        assertThat(testSolicitudes.getExperiencia()).isEqualTo(UPDATED_EXPERIENCIA);
        assertThat(testSolicitudes.getSoftSkill()).isEqualTo(UPDATED_SOFT_SKILL);
        assertThat(testSolicitudes.getNivelSoftSkill()).isEqualTo(UPDATED_NIVEL_SOFT_SKILL);
        assertThat(testSolicitudes.getApellidoCandidatoPropuesto()).isEqualTo(UPDATED_APELLIDO_CANDIDATO_PROPUESTO);
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
