package Proyecto.Backend.DWI.Security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /*1. extraer el dni osea el username del token */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /*2. Generar un token nuevo */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> extraClaims = new HashMap<>();

        extraClaims.put("rol", userDetails.getAuthorities().iterator().next().getAuthority());
        return buildToken(extraClaims,userDetails,jwtExpiration);
    }

    /*3. Validar si el token es correcto y no haya  expirado */
    public boolean isTokenValid(String token, UserDetails userDetails){
        
        final String username = extractUsername(token);
        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /*metodos: */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration){
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private boolean isTokenExpired (String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims ::getExpiration);
    }

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }
}
