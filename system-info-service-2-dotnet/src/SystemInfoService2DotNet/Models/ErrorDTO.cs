using System;
using System.Runtime.Serialization;
using System.Text;
using Newtonsoft.Json;

namespace SystemInfoService2DotNet.Models;

/// <summary>
/// </summary>
[DataContract]
public class ErrorDTO(string errorMessage) : IEquatable<ErrorDTO>
{
    /// <summary>
    ///     Error message
    /// </summary>
    /// <value>Error message</value>
    /// <example>Exception occurred when reading address id</example>
    [DataMember(Name = "errorMessage", EmitDefaultValue = false)]
    public string ErrorMessage { get; set; } = errorMessage;

    /// <summary>
    ///     Returns true if ErrorDTO instances are equal
    /// </summary>
    /// <param name="other">Instance of ErrorDTO to be compared</param>
    /// <returns>Boolean</returns>
    public bool Equals(ErrorDTO other)
    {
        if (other is null) return false;
        if (ReferenceEquals(this, other)) return true;

        return
            ErrorMessage == other.ErrorMessage ||
            (ErrorMessage != null &&
             ErrorMessage.Equals(other.ErrorMessage));
    }

    /// <summary>
    ///     Returns the string presentation of the object
    /// </summary>
    /// <returns>String presentation of the object</returns>
    public override string ToString()
    {
        var sb = new StringBuilder();
        sb.Append("class ErrorDTO {\n");
        sb.Append("  ErrorMessage: ").Append(ErrorMessage).Append("\n");
        sb.Append("}\n");
        return sb.ToString();
    }

    /// <summary>
    ///     Returns the JSON string presentation of the object
    /// </summary>
    /// <returns>JSON string presentation of the object</returns>
    public string ToJson()
    {
        return JsonConvert.SerializeObject(this, Formatting.Indented);
    }

    /// <summary>
    ///     Returns true if objects are equal
    /// </summary>
    /// <param name="obj">Object to be compared</param>
    /// <returns>Boolean</returns>
    public override bool Equals(object obj)
    {
        if (obj is null) return false;
        if (ReferenceEquals(this, obj)) return true;
        return obj.GetType() == GetType() && Equals((ErrorDTO)obj);
    }

    /// <summary>
    ///     Gets the hash code
    /// </summary>
    /// <returns>Hash code</returns>
    public override int GetHashCode()
    {
        unchecked // Overflow is fine, just wrap
        {
            var hashCode = 41;
            // Suitable nullity checks etc, of course :)
            if (ErrorMessage != null)
                hashCode = hashCode * 59 + ErrorMessage.GetHashCode();
            return hashCode;
        }
    }

    #region Operators

#pragma warning disable 1591

    public static bool operator ==(ErrorDTO left, ErrorDTO right)
    {
        return Equals(left, right);
    }

    public static bool operator !=(ErrorDTO left, ErrorDTO right)
    {
        return !Equals(left, right);
    }

#pragma warning restore 1591

    #endregion Operators
}