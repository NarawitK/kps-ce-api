--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

-- Started on 2023-02-09 15:43:02

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 27495)
-- Name: equipment_detail; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA equipment_detail;


ALTER SCHEMA equipment_detail OWNER TO postgres;

--
-- TOC entry 7 (class 2615 OID 27496)
-- Name: option; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA option;


ALTER SCHEMA option OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 264 (class 1259 OID 28869)
-- Name: cpu; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.cpu (
    id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    model character varying(30) NOT NULL,
    thread_count smallint,
    clock_speed double precision,
    clock_speed_unit_id integer
);


ALTER TABLE equipment_detail.cpu OWNER TO postgres;

--
-- TOC entry 265 (class 1259 OID 28884)
-- Name: cpu_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.cpu ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.cpu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 27497)
-- Name: laptop; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.laptop (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    model character varying(30) NOT NULL,
    pointer_device character varying(30),
    keyboard character varying(30),
    cdrom_type_id integer,
    cpu_id bigint NOT NULL,
    ram_id bigint NOT NULL
);


ALTER TABLE equipment_detail.laptop OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 27500)
-- Name: laptop_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.laptop ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.laptop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 27501)
-- Name: nic; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.nic (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    connection_type_id integer NOT NULL,
    connector_type_id integer NOT NULL,
    model character varying(20) NOT NULL,
    speed double precision NOT NULL,
    speed_unit_id integer NOT NULL
);


ALTER TABLE equipment_detail.nic OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 27504)
-- Name: nic_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.nic ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.nic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 27505)
-- Name: pc; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.pc (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    mb_manufacture_id integer NOT NULL,
    mb_model character varying(30) NOT NULL,
    pointer_device character varying(30),
    keyboard character varying(30),
    cdrom_type_id integer,
    psu_manufacture_id integer,
    psu_power double precision,
    psu_unit_id integer,
    cpu_id bigint NOT NULL,
    ram_id bigint NOT NULL,
    nic_id bigint,
    ups_id bigint,
    vga_id bigint
);


ALTER TABLE equipment_detail.pc OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 27508)
-- Name: pc_detail_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.pc ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.pc_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 27509)
-- Name: printer; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.printer (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    printer_type_id integer NOT NULL,
    model character varying(20) NOT NULL,
    ink_model character varying(30) NOT NULL,
    last_ink_reload_date timestamp with time zone
);


ALTER TABLE equipment_detail.printer OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 27512)
-- Name: printer_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.printer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.printer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 267 (class 1259 OID 28886)
-- Name: ram; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.ram (
    id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    model character varying(30) NOT NULL,
    memory_type character varying(10),
    capacity smallint,
    capacity_unit_id integer
);


ALTER TABLE equipment_detail.ram OWNER TO postgres;

--
-- TOC entry 266 (class 1259 OID 28885)
-- Name: ram_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.ram ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.ram_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 27513)
-- Name: scanner; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.scanner (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    model character varying(20) NOT NULL
);


ALTER TABLE equipment_detail.scanner OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 27516)
-- Name: scanner_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.scanner ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.scanner_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 27517)
-- Name: ups; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.ups (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    model character varying(20) NOT NULL,
    capacity double precision NOT NULL,
    capacity_unit_id integer NOT NULL
);


ALTER TABLE equipment_detail.ups OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 27520)
-- Name: ups_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.ups ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.ups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 228 (class 1259 OID 27521)
-- Name: vga; Type: TABLE; Schema: equipment_detail; Owner: postgres
--

CREATE TABLE equipment_detail.vga (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    manufacture_id integer NOT NULL,
    model character varying(20) NOT NULL,
    memory double precision NOT NULL,
    memory_unit_id integer NOT NULL
);


ALTER TABLE equipment_detail.vga OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 27524)
-- Name: vga_id_seq; Type: SEQUENCE; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE equipment_detail.vga ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME equipment_detail.vga_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 27525)
-- Name: cdrom_type; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.cdrom_type (
    id integer NOT NULL,
    type character varying(10) NOT NULL
);


ALTER TABLE option.cdrom_type OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 27528)
-- Name: cdrom_type_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.cdrom_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.cdrom_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 232 (class 1259 OID 27529)
-- Name: manufacture; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.manufacture (
    id integer NOT NULL,
    manufacture_category_id integer,
    name character varying(30) NOT NULL
);


ALTER TABLE option.manufacture OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 27532)
-- Name: manufacture_category; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.manufacture_category (
    id integer NOT NULL,
    category character varying(50) NOT NULL
);


ALTER TABLE option.manufacture_category OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 27535)
-- Name: manufacture_category_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.manufacture_category ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.manufacture_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 235 (class 1259 OID 27536)
-- Name: manufacture_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.manufacture ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.manufacture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 236 (class 1259 OID 27537)
-- Name: network_connection_type; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.network_connection_type (
    id integer NOT NULL,
    connection_type character varying(20) NOT NULL
);


ALTER TABLE option.network_connection_type OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 27540)
-- Name: network_connection_type_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.network_connection_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.network_connection_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 238 (class 1259 OID 27541)
-- Name: network_connector_type; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.network_connector_type (
    id integer NOT NULL,
    connector character varying(20) NOT NULL
);


ALTER TABLE option.network_connector_type OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 27544)
-- Name: network_connector_type_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.network_connector_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.network_connector_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 240 (class 1259 OID 27545)
-- Name: printer_type; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.printer_type (
    id integer NOT NULL,
    type character varying(40) NOT NULL
);


ALTER TABLE option.printer_type OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 27548)
-- Name: printer_type_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.printer_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.printer_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 242 (class 1259 OID 27549)
-- Name: typeof_unit; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.typeof_unit (
    id integer NOT NULL,
    type character varying(20) NOT NULL
);


ALTER TABLE option.typeof_unit OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 27552)
-- Name: unit; Type: TABLE; Schema: option; Owner: postgres
--

CREATE TABLE option.unit (
    id integer NOT NULL,
    name character varying(10) NOT NULL,
    type_id integer
);


ALTER TABLE option.unit OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 27555)
-- Name: unit_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.unit ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.unit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 245 (class 1259 OID 27556)
-- Name: unit_type_id_seq; Type: SEQUENCE; Schema: option; Owner: postgres
--

ALTER TABLE option.typeof_unit ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME option.unit_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 246 (class 1259 OID 27557)
-- Name: dept; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dept (
    id integer NOT NULL,
    name character varying(150) NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.dept OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 27561)
-- Name: dept_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.dept ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dept_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 248 (class 1259 OID 27562)
-- Name: equipment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipment (
    id bigint NOT NULL,
    equipment_type_id integer NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(250),
    internal_identifier text NOT NULL,
    serial_number text,
    active boolean DEFAULT false NOT NULL,
    import_date timestamp with time zone,
    register_date timestamp with time zone DEFAULT now() NOT NULL,
    update_date time with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.equipment OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 27570)
-- Name: equipment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.equipment ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.equipment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 250 (class 1259 OID 27571)
-- Name: equipment_location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipment_location (
    id bigint NOT NULL,
    dept_id integer NOT NULL,
    dept_sub_id integer NOT NULL,
    equipment_id bigint NOT NULL,
    assigned_date timestamp with time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT true NOT NULL,
    last_deactive_date timestamp with time zone
);


ALTER TABLE public.equipment_location OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 27576)
-- Name: equipment_location_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.equipment_location ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.equipment_location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 252 (class 1259 OID 27577)
-- Name: equipment_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipment_type (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.equipment_type OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 27580)
-- Name: equipment_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.equipment_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.equipment_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 254 (class 1259 OID 27581)
-- Name: event_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event_log (
    id bigint NOT NULL,
    logdate timestamp with time zone DEFAULT now() NOT NULL,
    operation character varying(10) NOT NULL,
    header text NOT NULL,
    detail text NOT NULL
);


ALTER TABLE public.event_log OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 27587)
-- Name: event_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.event_log ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.event_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 256 (class 1259 OID 27588)
-- Name: maintenance_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.maintenance_history (
    id bigint NOT NULL,
    equipment_id bigint NOT NULL,
    detail character varying(500) NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    done boolean DEFAULT false NOT NULL,
    done_date timestamp with time zone,
    created_by bigint NOT NULL
);


ALTER TABLE public.maintenance_history OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 27595)
-- Name: maintenance_history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.maintenance_history ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.maintenance_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 258 (class 1259 OID 27596)
-- Name: person_initial; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_initial (
    id integer NOT NULL,
    initial character varying(25) NOT NULL
);


ALTER TABLE public.person_initial OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 27599)
-- Name: person_initial_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person_initial ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.person_initial_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 260 (class 1259 OID 27600)
-- Name: sub_dept; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sub_dept (
    id integer NOT NULL,
    dept_id integer NOT NULL,
    name character varying(150) NOT NULL,
    active boolean DEFAULT true
);


ALTER TABLE public.sub_dept OWNER TO postgres;

--
-- TOC entry 261 (class 1259 OID 27604)
-- Name: sub_dept_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.sub_dept ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.sub_dept_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 262 (class 1259 OID 27605)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    initial_id bigint NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    last_login time with time zone,
    active boolean DEFAULT true NOT NULL,
    password text NOT NULL,
    username character varying(40) NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 263 (class 1259 OID 27611)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3414 (class 2606 OID 28873)
-- Name: cpu cpu_pkey; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.cpu
    ADD CONSTRAINT cpu_pkey PRIMARY KEY (id);


--
-- TOC entry 3317 (class 2606 OID 27613)
-- Name: laptop laptop_pkey_idx; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.laptop
    ADD CONSTRAINT laptop_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3323 (class 2606 OID 27615)
-- Name: nic nic_pkey_idx; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.nic
    ADD CONSTRAINT nic_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3334 (class 2606 OID 27617)
-- Name: pc pc_pkey_idx; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT pc_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3340 (class 2606 OID 27619)
-- Name: printer printer_pkey_idx; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.printer
    ADD CONSTRAINT printer_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3416 (class 2606 OID 28890)
-- Name: ram ram_pkey; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.ram
    ADD CONSTRAINT ram_pkey PRIMARY KEY (id);


--
-- TOC entry 3345 (class 2606 OID 27621)
-- Name: scanner scanner_pkey_idx; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.scanner
    ADD CONSTRAINT scanner_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3350 (class 2606 OID 27623)
-- Name: ups ups_pkey_idx; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.ups
    ADD CONSTRAINT ups_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3355 (class 2606 OID 27625)
-- Name: vga vga_pkey_idx; Type: CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.vga
    ADD CONSTRAINT vga_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3357 (class 2606 OID 27627)
-- Name: cdrom_type cdrom_type_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.cdrom_type
    ADD CONSTRAINT cdrom_type_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3360 (class 2606 OID 27631)
-- Name: manufacture manufacture_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.manufacture
    ADD CONSTRAINT manufacture_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3362 (class 2606 OID 27629)
-- Name: manufacture_category mcat_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.manufacture_category
    ADD CONSTRAINT mcat_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3366 (class 2606 OID 27635)
-- Name: network_connector_type netcon_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.network_connector_type
    ADD CONSTRAINT netcon_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3364 (class 2606 OID 27633)
-- Name: network_connection_type netconn_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.network_connection_type
    ADD CONSTRAINT netconn_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3368 (class 2606 OID 27637)
-- Name: printer_type printer_type_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.printer_type
    ADD CONSTRAINT printer_type_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3370 (class 2606 OID 27643)
-- Name: typeof_unit tou_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.typeof_unit
    ADD CONSTRAINT tou_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3372 (class 2606 OID 27965)
-- Name: unit unit_name_u; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.unit
    ADD CONSTRAINT unit_name_u UNIQUE (name);


--
-- TOC entry 3374 (class 2606 OID 27641)
-- Name: unit unit_pkey_idx; Type: CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.unit
    ADD CONSTRAINT unit_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3377 (class 2606 OID 27645)
-- Name: dept dept_pkey_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dept
    ADD CONSTRAINT dept_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3389 (class 2606 OID 27649)
-- Name: equipment_location eq_loc_pkey_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment_location
    ADD CONSTRAINT eq_loc_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3380 (class 2606 OID 27647)
-- Name: equipment equipment_identifier_U; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT "equipment_identifier_U" UNIQUE (internal_identifier);


--
-- TOC entry 3382 (class 2606 OID 27651)
-- Name: equipment equipment_pkey_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3385 (class 2606 OID 27653)
-- Name: equipment equipment_serial_number_U; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT "equipment_serial_number_U" UNIQUE (serial_number);


--
-- TOC entry 3392 (class 2606 OID 27655)
-- Name: equipment_type equipment_type_pkey_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment_type
    ADD CONSTRAINT equipment_type_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3396 (class 2606 OID 27657)
-- Name: event_log event_log_pkey_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_log
    ADD CONSTRAINT event_log_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3400 (class 2606 OID 27659)
-- Name: maintenance_history maintenance_pkey_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.maintenance_history
    ADD CONSTRAINT maintenance_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3402 (class 2606 OID 27661)
-- Name: person_initial person_init_id_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_initial
    ADD CONSTRAINT person_init_id_idx PRIMARY KEY (id);


--
-- TOC entry 3405 (class 2606 OID 27663)
-- Name: sub_dept subdept_pkey_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sub_dept
    ADD CONSTRAINT subdept_pkey_idx PRIMARY KEY (id);


--
-- TOC entry 3407 (class 2606 OID 27665)
-- Name: user user_email_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_email_unique UNIQUE (email);


--
-- TOC entry 3410 (class 2606 OID 27667)
-- Name: user user_id_idx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_id_idx PRIMARY KEY (id);


--
-- TOC entry 3412 (class 2606 OID 27967)
-- Name: user username_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT username_unique UNIQUE (username);


--
-- TOC entry 3311 (class 1259 OID 28906)
-- Name: fki_laptop_cpu_id; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX fki_laptop_cpu_id ON equipment_detail.laptop USING btree (cpu_id);


--
-- TOC entry 3312 (class 1259 OID 28912)
-- Name: fki_laptop_ram_id; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX fki_laptop_ram_id ON equipment_detail.laptop USING btree (ram_id);


--
-- TOC entry 3325 (class 1259 OID 28918)
-- Name: fki_pc_cpu_id; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX fki_pc_cpu_id ON equipment_detail.pc USING btree (cpu_id);


--
-- TOC entry 3326 (class 1259 OID 29459)
-- Name: fki_pc_nic_id; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX fki_pc_nic_id ON equipment_detail.pc USING btree (nic_id);


--
-- TOC entry 3327 (class 1259 OID 28924)
-- Name: fki_pc_ram_id; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX fki_pc_ram_id ON equipment_detail.pc USING btree (ram_id);


--
-- TOC entry 3328 (class 1259 OID 29465)
-- Name: fki_pc_ups_id; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX fki_pc_ups_id ON equipment_detail.pc USING btree (ups_id);


--
-- TOC entry 3329 (class 1259 OID 29471)
-- Name: fki_pc_vga_id; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX fki_pc_vga_id ON equipment_detail.pc USING btree (vga_id);


--
-- TOC entry 3313 (class 1259 OID 27668)
-- Name: laptop_cdrom_type_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX laptop_cdrom_type_id_fki ON equipment_detail.laptop USING btree (cdrom_type_id);


--
-- TOC entry 3314 (class 1259 OID 27671)
-- Name: laptop_eq_fk_idx; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX laptop_eq_fk_idx ON equipment_detail.laptop USING btree (equipment_id);


--
-- TOC entry 3315 (class 1259 OID 27672)
-- Name: laptop_manufacture_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX laptop_manufacture_id_fki ON equipment_detail.laptop USING btree (manufacture_id);


--
-- TOC entry 3351 (class 1259 OID 27675)
-- Name: mem_unit_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX mem_unit_id_fki ON equipment_detail.vga USING btree (memory_unit_id);


--
-- TOC entry 3318 (class 1259 OID 27676)
-- Name: nic_connection_type_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX nic_connection_type_fki ON equipment_detail.nic USING btree (connection_type_id);


--
-- TOC entry 3319 (class 1259 OID 27677)
-- Name: nic_connector_type_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX nic_connector_type_fki ON equipment_detail.nic USING btree (connector_type_id);


--
-- TOC entry 3320 (class 1259 OID 27678)
-- Name: nic_eq_fk_idx; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX nic_eq_fk_idx ON equipment_detail.nic USING btree (equipment_id);


--
-- TOC entry 3321 (class 1259 OID 27679)
-- Name: nic_manufac_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX nic_manufac_id_fki ON equipment_detail.nic USING btree (manufacture_id);


--
-- TOC entry 3324 (class 1259 OID 27681)
-- Name: nic_spd_unit_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX nic_spd_unit_id_fki ON equipment_detail.nic USING btree (speed_unit_id);


--
-- TOC entry 3330 (class 1259 OID 27682)
-- Name: pc_cdrom_type_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX pc_cdrom_type_id_fki ON equipment_detail.pc USING btree (cdrom_type_id);


--
-- TOC entry 3331 (class 1259 OID 27685)
-- Name: pc_eq_fk_idx; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX pc_eq_fk_idx ON equipment_detail.pc USING btree (equipment_id);


--
-- TOC entry 3332 (class 1259 OID 27686)
-- Name: pc_mb_manufacture_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX pc_mb_manufacture_id_fki ON equipment_detail.pc USING btree (mb_manufacture_id);


--
-- TOC entry 3335 (class 1259 OID 27687)
-- Name: pc_psu_manufacture_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX pc_psu_manufacture_id_fki ON equipment_detail.pc USING btree (psu_manufacture_id);


--
-- TOC entry 3336 (class 1259 OID 27688)
-- Name: pc_psu_unit_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX pc_psu_unit_id_fki ON equipment_detail.pc USING btree (psu_unit_id);


--
-- TOC entry 3337 (class 1259 OID 27691)
-- Name: printer_eq_fk_idx; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX printer_eq_fk_idx ON equipment_detail.printer USING btree (equipment_id);


--
-- TOC entry 3338 (class 1259 OID 27692)
-- Name: printer_manufac_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX printer_manufac_id_fki ON equipment_detail.printer USING btree (manufacture_id);


--
-- TOC entry 3341 (class 1259 OID 27693)
-- Name: printer_type_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX printer_type_id_fki ON equipment_detail.printer USING btree (printer_type_id);


--
-- TOC entry 3342 (class 1259 OID 27694)
-- Name: scanner_eq_fk_idx; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX scanner_eq_fk_idx ON equipment_detail.scanner USING btree (equipment_id);


--
-- TOC entry 3343 (class 1259 OID 27695)
-- Name: scanner_manufac_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX scanner_manufac_id_fki ON equipment_detail.scanner USING btree (manufacture_id);


--
-- TOC entry 3346 (class 1259 OID 27696)
-- Name: ups_cap_unit_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX ups_cap_unit_id_fki ON equipment_detail.ups USING btree (capacity_unit_id);


--
-- TOC entry 3347 (class 1259 OID 27697)
-- Name: ups_eq_fk_idx; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX ups_eq_fk_idx ON equipment_detail.ups USING btree (equipment_id);


--
-- TOC entry 3348 (class 1259 OID 27698)
-- Name: ups_manufac_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX ups_manufac_id_fki ON equipment_detail.ups USING btree (manufacture_id);


--
-- TOC entry 3352 (class 1259 OID 27700)
-- Name: vga_eq_fk_idx; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX vga_eq_fk_idx ON equipment_detail.vga USING btree (equipment_id);


--
-- TOC entry 3353 (class 1259 OID 27701)
-- Name: vga_manufac_id_fki; Type: INDEX; Schema: equipment_detail; Owner: postgres
--

CREATE INDEX vga_manufac_id_fki ON equipment_detail.vga USING btree (manufacture_id);


--
-- TOC entry 3358 (class 1259 OID 27711)
-- Name: main_category_id_fki; Type: INDEX; Schema: option; Owner: postgres
--

CREATE INDEX main_category_id_fki ON option.manufacture USING btree (manufacture_category_id);


--
-- TOC entry 3375 (class 1259 OID 27712)
-- Name: unit_type_id_fki; Type: INDEX; Schema: option; Owner: postgres
--

CREATE INDEX unit_type_id_fki ON option.unit USING btree (type_id);


--
-- TOC entry 3378 (class 1259 OID 27721)
-- Name: dept_pkey_isa_cidx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX dept_pkey_isa_cidx ON public.dept USING btree (id, active);


--
-- TOC entry 3387 (class 1259 OID 27727)
-- Name: eq_loc_equip_id_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX eq_loc_equip_id_idx ON public.equipment_location USING btree (equipment_id);


--
-- TOC entry 3390 (class 1259 OID 27723)
-- Name: eq_loc_pkey_isa_cidx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX eq_loc_pkey_isa_cidx ON public.equipment_location USING btree (id, active);


--
-- TOC entry 3383 (class 1259 OID 27725)
-- Name: equipment_pkey_isa_cidx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX equipment_pkey_isa_cidx ON public.equipment USING btree (id, active);


--
-- TOC entry 3386 (class 1259 OID 27728)
-- Name: equipment_type_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX equipment_type_fk_idx ON public.equipment USING btree (equipment_type_id);


--
-- TOC entry 3393 (class 1259 OID 27962)
-- Name: event_log_logdate_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX event_log_logdate_idx ON public.event_log USING btree (logdate);


--
-- TOC entry 3394 (class 1259 OID 27963)
-- Name: event_log_ops_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX event_log_ops_idx ON public.event_log USING btree (operation);


--
-- TOC entry 3397 (class 1259 OID 27961)
-- Name: maintenance_done_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX maintenance_done_idx ON public.maintenance_history USING btree (done);


--
-- TOC entry 3398 (class 1259 OID 27960)
-- Name: maintenance_equip_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX maintenance_equip_fk_idx ON public.maintenance_history USING btree (equipment_id);


--
-- TOC entry 3403 (class 1259 OID 27730)
-- Name: subdept_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX subdept_fk_idx ON public.sub_dept USING btree (dept_id);


--
-- TOC entry 3408 (class 1259 OID 27729)
-- Name: user_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX user_fk_idx ON public."user" USING btree (initial_id);


--
-- TOC entry 3458 (class 2606 OID 28879)
-- Name: cpu clock_speed_unit_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.cpu
    ADD CONSTRAINT "clock_speed_unit_FK" FOREIGN KEY (clock_speed_unit_id) REFERENCES option.unit(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3459 (class 2606 OID 28874)
-- Name: cpu cpu_manufacture_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.cpu
    ADD CONSTRAINT "cpu_manufacture_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3417 (class 2606 OID 27734)
-- Name: laptop laptop_cdrom_type_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.laptop
    ADD CONSTRAINT "laptop_cdrom_type_id_FK" FOREIGN KEY (cdrom_type_id) REFERENCES option.cdrom_type(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3418 (class 2606 OID 28901)
-- Name: laptop laptop_cpu_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.laptop
    ADD CONSTRAINT "laptop_cpu_id_FK" FOREIGN KEY (cpu_id) REFERENCES equipment_detail.cpu(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3419 (class 2606 OID 27749)
-- Name: laptop laptop_equipment_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.laptop
    ADD CONSTRAINT "laptop_equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3420 (class 2606 OID 27754)
-- Name: laptop laptop_manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.laptop
    ADD CONSTRAINT "laptop_manufacture_id_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3421 (class 2606 OID 28907)
-- Name: laptop laptop_ram_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.laptop
    ADD CONSTRAINT "laptop_ram_id_FK" FOREIGN KEY (ram_id) REFERENCES equipment_detail.ram(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3437 (class 2606 OID 27769)
-- Name: printer manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.printer
    ADD CONSTRAINT "manufacture_id_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3422 (class 2606 OID 27774)
-- Name: nic nic_connection_type_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.nic
    ADD CONSTRAINT "nic_connection_type_FK" FOREIGN KEY (connection_type_id) REFERENCES option.network_connection_type(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3423 (class 2606 OID 27779)
-- Name: nic nic_connector_type_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.nic
    ADD CONSTRAINT "nic_connector_type_FK" FOREIGN KEY (connector_type_id) REFERENCES option.network_connector_type(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 3424 (class 2606 OID 27784)
-- Name: nic nic_equipment_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.nic
    ADD CONSTRAINT "nic_equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3425 (class 2606 OID 27789)
-- Name: nic nic_manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.nic
    ADD CONSTRAINT "nic_manufacture_id_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3426 (class 2606 OID 27799)
-- Name: nic nic_speed_unit_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.nic
    ADD CONSTRAINT "nic_speed_unit_id_FK" FOREIGN KEY (speed_unit_id) REFERENCES option.unit(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3427 (class 2606 OID 27804)
-- Name: pc pc_cdrom_type_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_cdrom_type_id_FK" FOREIGN KEY (cdrom_type_id) REFERENCES option.cdrom_type(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3428 (class 2606 OID 28913)
-- Name: pc pc_cpu_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_cpu_id_FK" FOREIGN KEY (cpu_id) REFERENCES equipment_detail.cpu(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3429 (class 2606 OID 27819)
-- Name: pc pc_equipment_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3430 (class 2606 OID 27824)
-- Name: pc pc_mb_manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_mb_manufacture_id_FK" FOREIGN KEY (mb_manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3431 (class 2606 OID 29454)
-- Name: pc pc_nic_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_nic_id_FK" FOREIGN KEY (nic_id) REFERENCES equipment_detail.nic(id) ON UPDATE RESTRICT ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3432 (class 2606 OID 27829)
-- Name: pc pc_psu_manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_psu_manufacture_id_FK" FOREIGN KEY (psu_manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3433 (class 2606 OID 27834)
-- Name: pc pc_psu_unit_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_psu_unit_id_FK" FOREIGN KEY (psu_unit_id) REFERENCES option.unit(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3434 (class 2606 OID 28919)
-- Name: pc pc_ram_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_ram_id_FK" FOREIGN KEY (ram_id) REFERENCES equipment_detail.ram(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3435 (class 2606 OID 29460)
-- Name: pc pc_ups_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_ups_id_FK" FOREIGN KEY (ups_id) REFERENCES equipment_detail.ups(id) ON UPDATE RESTRICT ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3436 (class 2606 OID 29466)
-- Name: pc pc_vga_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.pc
    ADD CONSTRAINT "pc_vga_id_FK" FOREIGN KEY (vga_id) REFERENCES equipment_detail.vga(id) ON UPDATE RESTRICT ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3438 (class 2606 OID 27849)
-- Name: printer printer_equipment_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.printer
    ADD CONSTRAINT "printer_equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3439 (class 2606 OID 27854)
-- Name: printer printer_type_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.printer
    ADD CONSTRAINT "printer_type_id_FK" FOREIGN KEY (printer_type_id) REFERENCES option.printer_type(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3460 (class 2606 OID 28891)
-- Name: ram ram_capacity_unit_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.ram
    ADD CONSTRAINT "ram_capacity_unit_id_FK" FOREIGN KEY (capacity_unit_id) REFERENCES option.unit(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3461 (class 2606 OID 28896)
-- Name: ram ram_manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.ram
    ADD CONSTRAINT "ram_manufacture_id_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3440 (class 2606 OID 27859)
-- Name: scanner scanner_equipment_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.scanner
    ADD CONSTRAINT "scanner_equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3441 (class 2606 OID 27864)
-- Name: scanner scanner_manufac_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.scanner
    ADD CONSTRAINT "scanner_manufac_id_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3442 (class 2606 OID 27869)
-- Name: ups ups_cap_unit_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.ups
    ADD CONSTRAINT "ups_cap_unit_id_FK" FOREIGN KEY (capacity_unit_id) REFERENCES option.unit(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3443 (class 2606 OID 27874)
-- Name: ups ups_equipment_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.ups
    ADD CONSTRAINT "ups_equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3444 (class 2606 OID 27879)
-- Name: ups ups_manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.ups
    ADD CONSTRAINT "ups_manufacture_id_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3445 (class 2606 OID 27889)
-- Name: vga vga_equipment_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.vga
    ADD CONSTRAINT "vga_equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3446 (class 2606 OID 27894)
-- Name: vga vga_manufacture_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.vga
    ADD CONSTRAINT "vga_manufacture_id_FK" FOREIGN KEY (manufacture_id) REFERENCES option.manufacture(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3447 (class 2606 OID 27899)
-- Name: vga vga_memory_unit_id_FK; Type: FK CONSTRAINT; Schema: equipment_detail; Owner: postgres
--

ALTER TABLE ONLY equipment_detail.vga
    ADD CONSTRAINT "vga_memory_unit_id_FK" FOREIGN KEY (memory_unit_id) REFERENCES option.unit(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3448 (class 2606 OID 27909)
-- Name: manufacture main_category_id_FK; Type: FK CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.manufacture
    ADD CONSTRAINT "main_category_id_FK" FOREIGN KEY (manufacture_category_id) REFERENCES option.manufacture_category(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 3449 (class 2606 OID 27914)
-- Name: unit unit_type_id_FK; Type: FK CONSTRAINT; Schema: option; Owner: postgres
--

ALTER TABLE ONLY option.unit
    ADD CONSTRAINT "unit_type_id_FK" FOREIGN KEY (type_id) REFERENCES option.typeof_unit(id) NOT VALID;


--
-- TOC entry 3454 (class 2606 OID 27919)
-- Name: maintenance_history created_by_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.maintenance_history
    ADD CONSTRAINT "created_by_FK" FOREIGN KEY (created_by) REFERENCES public."user"(id) ON UPDATE CASCADE;


--
-- TOC entry 3451 (class 2606 OID 27924)
-- Name: equipment_location dept_id_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment_location
    ADD CONSTRAINT "dept_id_FK" FOREIGN KEY (dept_id) REFERENCES public.dept(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3456 (class 2606 OID 27929)
-- Name: sub_dept dept_id_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sub_dept
    ADD CONSTRAINT "dept_id_FK" FOREIGN KEY (dept_id) REFERENCES public.dept(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3452 (class 2606 OID 27934)
-- Name: equipment_location dept_sub_id_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment_location
    ADD CONSTRAINT "dept_sub_id_FK" FOREIGN KEY (dept_sub_id) REFERENCES public.sub_dept(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 3450 (class 2606 OID 27939)
-- Name: equipment equipment_equipment_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_equipment_type_id_fkey FOREIGN KEY (equipment_type_id) REFERENCES public.equipment_type(id) NOT VALID;


--
-- TOC entry 3455 (class 2606 OID 27944)
-- Name: maintenance_history equipment_id_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.maintenance_history
    ADD CONSTRAINT "equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE CASCADE;


--
-- TOC entry 3453 (class 2606 OID 27949)
-- Name: equipment_location equipment_id_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment_location
    ADD CONSTRAINT "equipment_id_FK" FOREIGN KEY (equipment_id) REFERENCES public.equipment(id) ON UPDATE RESTRICT ON DELETE CASCADE;


--
-- TOC entry 3457 (class 2606 OID 27954)
-- Name: user user_initial_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_initial_id_fkey FOREIGN KEY (initial_id) REFERENCES public.person_initial(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
