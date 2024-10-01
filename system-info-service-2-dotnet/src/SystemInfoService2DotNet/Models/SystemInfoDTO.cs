using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using Newtonsoft.Json;

namespace SystemInfoService2DotNet.Models;

/// <summary>
/// </summary>
[DataContract]
public class SystemInfoDTO(string ipAddress, List<ProcessDTO> processes, long diskSpace, double lastBootTime)
    : IEquatable<SystemInfoDTO>
{
    /// <summary>
    ///     IP Adress of container
    /// </summary>
    /// <value>IP Adress of container</value>
    /// <example>207.11.119.205</example>
    [DataMember(Name = "ipAddress", EmitDefaultValue = false)]
    public string IpAddress { get; set; } = ipAddress;

    /// <summary>
    ///     List of running processes
    /// </summary>
    /// <value>List of running processes</value>
    [DataMember(Name = "processes", EmitDefaultValue = false)]
    public List<ProcessDTO> Processes { get; set; } = processes;

    /// <summary>
    ///     Available disk space in MB
    /// </summary>
    /// <value>Available disk space in MB</value>
    /// <example>12</example>
    [DataMember(Name = "diskSpace", EmitDefaultValue = true)]
    public long DiskSpace { get; set; } = diskSpace;

    /// <summary>
    ///     Time since last boot in seconds
    /// </summary>
    /// <value>Time since last boot in seconds</value>
    /// <example>5541.3</example>
    [DataMember(Name = "lastBootTime", EmitDefaultValue = true)]
    public double LastBootTime { get; set; } = lastBootTime;

    /// <summary>
    ///     Returns true if SystemInfoDTO instances are equal
    /// </summary>
    /// <param name="other">Instance of SystemInfoDTO to be compared</param>
    /// <returns>Boolean</returns>
    public bool Equals(SystemInfoDTO other)
    {
        if (other is null) return false;
        if (ReferenceEquals(this, other)) return true;

        return
            (
                IpAddress == other.IpAddress ||
                (IpAddress != null &&
                 IpAddress.Equals(other.IpAddress))
            ) &&
            (
                Processes == other.Processes ||
                (Processes != null &&
                 other.Processes != null &&
                 Processes.SequenceEqual(other.Processes))
            ) &&
            (
                DiskSpace == other.DiskSpace ||
                DiskSpace.Equals(other.DiskSpace)
            ) &&
            (
                LastBootTime == other.LastBootTime ||
                LastBootTime.Equals(other.LastBootTime)
            );
    }

    /// <summary>
    ///     Returns the string presentation of the object
    /// </summary>
    /// <returns>String presentation of the object</returns>
    public override string ToString()
    {
        var sb = new StringBuilder();
        sb.Append("class SystemInfoDTO {\n");
        sb.Append("  IpAddress: ").Append(IpAddress).Append("\n");
        sb.Append("  Processes: ").Append(Processes).Append("\n");
        sb.Append("  DiskSpace: ").Append(DiskSpace).Append("\n");
        sb.Append("  LastBootTime: ").Append(LastBootTime).Append("\n");
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
        return obj.GetType() == GetType() && Equals((SystemInfoDTO)obj);
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
            if (IpAddress != null)
                hashCode = hashCode * 59 + IpAddress.GetHashCode();
            if (Processes != null)
                hashCode = hashCode * 59 + Processes.GetHashCode();

            hashCode = hashCode * 59 + DiskSpace.GetHashCode();

            hashCode = hashCode * 59 + LastBootTime.GetHashCode();
            return hashCode;
        }
    }

    #region Operators

#pragma warning disable 1591

    public static bool operator ==(SystemInfoDTO left, SystemInfoDTO right)
    {
        return Equals(left, right);
    }

    public static bool operator !=(SystemInfoDTO left, SystemInfoDTO right)
    {
        return !Equals(left, right);
    }

#pragma warning restore 1591

    #endregion Operators
}