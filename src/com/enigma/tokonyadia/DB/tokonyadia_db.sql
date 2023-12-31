PGDMP     8        
        	    {            tokonyadia_db #   14.9 (Ubuntu 14.9-0ubuntu0.22.04.1) #   14.9 (Ubuntu 14.9-0ubuntu0.22.04.1) .    K           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            L           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            M           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            N           1262    16725    tokonyadia_db    DATABASE     b   CREATE DATABASE tokonyadia_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';
    DROP DATABASE tokonyadia_db;
                postgres    false            �            1259    16727 
   m_customer    TABLE     �   CREATE TABLE public.m_customer (
    id integer NOT NULL,
    name character varying(100),
    address character varying(100),
    phone_number character varying(16),
    email character varying(50)
);
    DROP TABLE public.m_customer;
       public         heap    postgres    false            �            1259    16726    m_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.m_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.m_customer_id_seq;
       public          postgres    false    210            O           0    0    m_customer_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.m_customer_id_seq OWNED BY public.m_customer.id;
          public          postgres    false    209            �            1259    16747 	   m_product    TABLE     �   CREATE TABLE public.m_product (
    id integer NOT NULL,
    name character varying(100),
    description text,
    price bigint,
    stock integer,
    store_id integer NOT NULL
);
    DROP TABLE public.m_product;
       public         heap    postgres    false            �            1259    16746    m_product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.m_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.m_product_id_seq;
       public          postgres    false    214            P           0    0    m_product_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.m_product_id_seq OWNED BY public.m_product.id;
          public          postgres    false    213            �            1259    16738    m_store    TABLE     �   CREATE TABLE public.m_store (
    id integer NOT NULL,
    siup_number character varying(20),
    name character varying(100),
    address character varying(100),
    phone_number character varying(16)
);
    DROP TABLE public.m_store;
       public         heap    postgres    false            �            1259    16737    m_store_id_seq    SEQUENCE     �   CREATE SEQUENCE public.m_store_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.m_store_id_seq;
       public          postgres    false    212            Q           0    0    m_store_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.m_store_id_seq OWNED BY public.m_store.id;
          public          postgres    false    211            �            1259    16761    t_transaction    TABLE     |   CREATE TABLE public.t_transaction (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    transaction_date date
);
 !   DROP TABLE public.t_transaction;
       public         heap    postgres    false            �            1259    16773    t_transaction_detail    TABLE     �   CREATE TABLE public.t_transaction_detail (
    id integer NOT NULL,
    transaction_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer,
    price bigint
);
 (   DROP TABLE public.t_transaction_detail;
       public         heap    postgres    false            �            1259    16772    t_transaction_detail_id_seq    SEQUENCE     �   CREATE SEQUENCE public.t_transaction_detail_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.t_transaction_detail_id_seq;
       public          postgres    false    218            R           0    0    t_transaction_detail_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.t_transaction_detail_id_seq OWNED BY public.t_transaction_detail.id;
          public          postgres    false    217            �            1259    16760    t_transaction_id_seq    SEQUENCE     �   CREATE SEQUENCE public.t_transaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.t_transaction_id_seq;
       public          postgres    false    216            S           0    0    t_transaction_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.t_transaction_id_seq OWNED BY public.t_transaction.id;
          public          postgres    false    215            �           2604    16730    m_customer id    DEFAULT     n   ALTER TABLE ONLY public.m_customer ALTER COLUMN id SET DEFAULT nextval('public.m_customer_id_seq'::regclass);
 <   ALTER TABLE public.m_customer ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            �           2604    16750    m_product id    DEFAULT     l   ALTER TABLE ONLY public.m_product ALTER COLUMN id SET DEFAULT nextval('public.m_product_id_seq'::regclass);
 ;   ALTER TABLE public.m_product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213    214            �           2604    16741 
   m_store id    DEFAULT     h   ALTER TABLE ONLY public.m_store ALTER COLUMN id SET DEFAULT nextval('public.m_store_id_seq'::regclass);
 9   ALTER TABLE public.m_store ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            �           2604    16764    t_transaction id    DEFAULT     t   ALTER TABLE ONLY public.t_transaction ALTER COLUMN id SET DEFAULT nextval('public.t_transaction_id_seq'::regclass);
 ?   ALTER TABLE public.t_transaction ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    16776    t_transaction_detail id    DEFAULT     �   ALTER TABLE ONLY public.t_transaction_detail ALTER COLUMN id SET DEFAULT nextval('public.t_transaction_detail_id_seq'::regclass);
 F   ALTER TABLE public.t_transaction_detail ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            @          0    16727 
   m_customer 
   TABLE DATA           L   COPY public.m_customer (id, name, address, phone_number, email) FROM stdin;
    public          postgres    false    210   �4       D          0    16747 	   m_product 
   TABLE DATA           R   COPY public.m_product (id, name, description, price, stock, store_id) FROM stdin;
    public          postgres    false    214   Q5       B          0    16738    m_store 
   TABLE DATA           O   COPY public.m_store (id, siup_number, name, address, phone_number) FROM stdin;
    public          postgres    false    212   �5       F          0    16761    t_transaction 
   TABLE DATA           J   COPY public.t_transaction (id, customer_id, transaction_date) FROM stdin;
    public          postgres    false    216   R6       H          0    16773    t_transaction_detail 
   TABLE DATA           _   COPY public.t_transaction_detail (id, transaction_id, product_id, quantity, price) FROM stdin;
    public          postgres    false    218   o6       T           0    0    m_customer_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.m_customer_id_seq', 4, true);
          public          postgres    false    209            U           0    0    m_product_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.m_product_id_seq', 10, true);
          public          postgres    false    213            V           0    0    m_store_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.m_store_id_seq', 1, false);
          public          postgres    false    211            W           0    0    t_transaction_detail_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.t_transaction_detail_id_seq', 1, false);
          public          postgres    false    217            X           0    0    t_transaction_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.t_transaction_id_seq', 1, false);
          public          postgres    false    215            �           2606    16736    m_customer m_customer_email_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.m_customer
    ADD CONSTRAINT m_customer_email_key UNIQUE (email);
 I   ALTER TABLE ONLY public.m_customer DROP CONSTRAINT m_customer_email_key;
       public            postgres    false    210            �           2606    16734 &   m_customer m_customer_phone_number_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.m_customer
    ADD CONSTRAINT m_customer_phone_number_key UNIQUE (phone_number);
 P   ALTER TABLE ONLY public.m_customer DROP CONSTRAINT m_customer_phone_number_key;
       public            postgres    false    210            �           2606    16732    m_customer m_customer_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.m_customer
    ADD CONSTRAINT m_customer_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.m_customer DROP CONSTRAINT m_customer_pkey;
       public            postgres    false    210            �           2606    16754    m_product m_product_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.m_product
    ADD CONSTRAINT m_product_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.m_product DROP CONSTRAINT m_product_pkey;
       public            postgres    false    214            �           2606    16745     m_store m_store_phone_number_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.m_store
    ADD CONSTRAINT m_store_phone_number_key UNIQUE (phone_number);
 J   ALTER TABLE ONLY public.m_store DROP CONSTRAINT m_store_phone_number_key;
       public            postgres    false    212            �           2606    16743    m_store m_store_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.m_store
    ADD CONSTRAINT m_store_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.m_store DROP CONSTRAINT m_store_pkey;
       public            postgres    false    212            �           2606    16778 .   t_transaction_detail t_transaction_detail_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.t_transaction_detail
    ADD CONSTRAINT t_transaction_detail_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.t_transaction_detail DROP CONSTRAINT t_transaction_detail_pkey;
       public            postgres    false    218            �           2606    16766     t_transaction t_transaction_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.t_transaction DROP CONSTRAINT t_transaction_pkey;
       public            postgres    false    216            �           2606    16767    t_transaction fk_customer_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES public.m_customer(id);
 F   ALTER TABLE ONLY public.t_transaction DROP CONSTRAINT fk_customer_id;
       public          postgres    false    3237    216    210            �           2606    16784 "   t_transaction_detail fk_product_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_transaction_detail
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.m_product(id);
 L   ALTER TABLE ONLY public.t_transaction_detail DROP CONSTRAINT fk_product_id;
       public          postgres    false    3243    214    218            �           2606    16755    m_product fk_store_id    FK CONSTRAINT     w   ALTER TABLE ONLY public.m_product
    ADD CONSTRAINT fk_store_id FOREIGN KEY (store_id) REFERENCES public.m_store(id);
 ?   ALTER TABLE ONLY public.m_product DROP CONSTRAINT fk_store_id;
       public          postgres    false    3241    212    214            �           2606    16779 &   t_transaction_detail fk_transaction_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_transaction_detail
    ADD CONSTRAINT fk_transaction_id FOREIGN KEY (transaction_id) REFERENCES public.t_transaction(id);
 P   ALTER TABLE ONLY public.t_transaction_detail DROP CONSTRAINT fk_transaction_id;
       public          postgres    false    216    3245    218            @   U   x�3��N-���4�04�4��0165����S�L̨LT�+�̀�pH�M���K���2��,*K�SpqquAWn`�HZc���� ؓ"9      D   j   x�U��� ��lT`���/(�?��EML��&o����U<��SRnKJ�Ip��&���WJ ��Gi\v�G4�:��T�Φ�U���鞃�r"�h*�      B   w   x�3⬬45426����W�ML)-JT(�LTpqqu�����NMJL/-N�S(J�䥖sXY[�ZqrVT �01��Q����QR��镣�����W���bl	��\1z\\\ ��"�      F      x������ � �      H      x������ � �     