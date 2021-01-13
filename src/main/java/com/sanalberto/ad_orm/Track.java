package com.sanalberto.ad_orm;
// Generated 11 ene. 2021 21:27:48 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Track generated by hbm2java
 */
@Entity
@Table(name = "Track", catalog = "chinook")
public class Track implements java.io.Serializable {

	private Integer trackId;
	private Album album;
	private Genre genre;
	private MediaType mediaType;
	private String name;
	private String composer;
	private int milliseconds;
	private Integer bytes;
	private BigDecimal unitPrice;
	private Set<Playlist> playlists = new HashSet<Playlist>(0);
	private Set<InvoiceLine> invoiceLines = new HashSet<InvoiceLine>(0);

	public Track() {
	}

	public Track(MediaType mediaType, String name, int milliseconds, BigDecimal unitPrice) {
		this.mediaType = mediaType;
		this.name = name;
		this.milliseconds = milliseconds;
		this.unitPrice = unitPrice;
	}

	public Track(Album album, Genre genre, MediaType mediaType, String name, String composer, int milliseconds,
			Integer bytes, BigDecimal unitPrice, Set<Playlist> playlists, Set<InvoiceLine> invoiceLines) {
		this.album = album;
		this.genre = genre;
		this.mediaType = mediaType;
		this.name = name;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitPrice = unitPrice;
		this.playlists = playlists;
		this.invoiceLines = invoiceLines;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "TrackId", unique = true, nullable = false)
	public Integer getTrackId() {
		return this.trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AlbumId")
	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GenreId")
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MediaTypeId", nullable = false)
	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	@Column(name = "Name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Composer", length = 220)
	public String getComposer() {
		return this.composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	@Column(name = "Milliseconds", nullable = false)
	public int getMilliseconds() {
		return this.milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	@Column(name = "Bytes")
	public Integer getBytes() {
		return this.bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	@Column(name = "UnitPrice", nullable = false, precision = 10)
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tracks")
	public Set<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
	public Set<InvoiceLine> getInvoiceLines() {
		return this.invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

}
